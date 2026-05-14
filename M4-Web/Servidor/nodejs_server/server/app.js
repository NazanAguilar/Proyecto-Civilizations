const express = require('express');
const fs = require('fs');
const path = require('path');
const hbs = require('hbs');
const MySQL = require('./utilsMySQL');

const app = express();
const port = 3000;

// Detectar si estem al Proxmox (si és pm2)
const isProxmox = !!process.env.PM2_HOME;

// Iniciar connexió MySQL
const db = new MySQL();
if (!isProxmox) {
  db.init({
    host: '127.0.0.1',
    port: 3306,
    user: 'root',
    password: 'mysqlocal',
    database: 'civi_mnr'
  });
} else {
  db.init({
    host: '127.0.0.1',
    port: 3306,
    user: 'pruebas',
    password: 'civil_mnr',
    database: 'civi_mnr'
  });
}

// Static files - ONLY ONCE
app.use(express.static('public'))
app.use(express.urlencoded({ extended: true }))

// Disable cache
app.use((req, res, next) => {
  res.setHeader('Cache-Control', 'no-store, no-cache, must-revalidate, proxy-revalidate');
  res.setHeader('Pragma', 'no-cache');
  res.setHeader('Expires', '0');
  res.setHeader('Surrogate-Control', 'no-store');
  next();
});

// Handlebars
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'hbs');

// Registrar "Helpers .hbs" aquí
hbs.registerHelper('eq', (a, b) => a == b);
hbs.registerHelper('gt', (a, b) => a > b);

// Partials de Handlebars
hbs.registerPartials(path.join(__dirname, 'views', 'partials'));

// Pantalla principal
app.get('/', async (req, res) => {
  try {

    const batallasRows = await db.query(`
      select bs.civilization_id as ID_Civ,
        cs.name as Nombre, 
        bs.id_battle as ID_Bat,
        bs.wood_acquired as Madera_conseguida,
        bs.iron_acquired as Hierro_conseguido, 
        bs.civ_enem as ID_Civ_Enem
      from battle_stats bs
      join civilization_stats cs on bs.civilization_id = cs.civilization_id
      order by bs.id_battle desc,bs.civilization_id 
      limit 4;
    `)

    if (!batallasRows || batallasRows.length === 0) {
      return res.status(404).send('Batalla no encontrada')
    }

    const batallasJson = db.table_to_json(batallasRows, {
  ID_Civ: 'number',
  Nombre: 'string',
  ID_Bat: 'number',
  Madera_conseguida: 'number',
  Hierro_conseguido: 'number',
  ID_Civ_Enem: 'number',
  Log: 'string'
});
    // Renderitzar la plantilla amb les dades
    res.render('principal', {
      batallas: batallasJson
    });
  } catch (err) {
    console.error(err);
    res.status(500).send('Error consultando la BBDD');
  }
});

//Pantalla batallas
app.get('/batallas', async (req, res) => {
  try {
 
    const batallasID = parseInt(req.query.id, 10)

    // Obtenir les dades de la base de dades
    const Total = await db.query(`select count(*) div 2 as Total from battle_stats;`);

    const batallasRows = await db.query(`
      select bs.civilization_id as ID_Civ,
        cs.name as Nombre, 
        bs.id_battle as ID_Bat,
        bs.wood_acquired as Madera_conseguida,
        bs.iron_acquired as Hierro_conseguido, 
        bs.civ_enem as ID_Civ_Enem
      from battle_stats bs
      join civilization_stats cs on bs.civilization_id = cs.civilization_id
      order by bs.id_battle,bs.civilization_id;`);

    // Transformar les dades a JSON (per les plantilles .hbs)
    const batallasJson = db.table_to_json(batallasRows, {Nombre: 'string', ID_Bat: 'number', Madera_conseguida: 'number', Hierro_conseguido: 'number', ID_Civ_Enem: 'number'});

       // Construir l'objecte de dades per a la plantilla
    const data = {
      batallas: batallasJson,
      Total: Total[0].Total

       };

    // Renderitzar la plantilla amb les dades
    res.render('batallas', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error al consultar batallas');
  }
});


//Pantalla civilizacion
app.get('/civilizacion', async (req, res) => {
  try {

    // Obtenir les dades de la base de dades
    const civilizacionRows = await db.query(`
        select civilization_id as ID,
          name as Nombre,
          wood_amount as Cantidad_madera,
          iron_amount as Cantidad_hierro,
          food_amount as Cantidad_comida,
          mana_amount as Cantidad_mana,
          magicTower_counter as Contador_torre_magica,
          church_counter as Contador_iglesia,
          farm_counter as Contador_granja,
          smithy_counter as Contador_herreria,
          carpentry_counter as Contador_carpinteria,
          technology_defense_level as Nivel_defensa_tecnologica,
          technology_attack_level as Nivel_ataque_tecnologico,
          battles_counter as Contador_batallas
        from civilization_stats;
      `);

    // Transformar les dades a JSON (per les plantilles .hbs)
    const civilizacionJson = db.table_to_json(civilizacionRows, {
      Nombre: 'string',
      Cantidad_madera: 'number',
      Cantidad_hierro: 'number',
      Cantidad_comida: 'number',
      Cantidad_mana: 'number',
      Contador_torre_magica: 'number',
      Contador_iglesia: 'number',
      Contador_granja: 'number',
      Contador_herreria: 'number',
      Contador_carpinteria: 'number',
      Nivel_defensa_tecnologica: 'number',
      Nivel_ataque_tecnologico: 'number',
      Contador_batallas: 'number'
    });

    // Construir l'objecte de dades per a la plantilla
    const data = {
      civilizacion: civilizacionJson
    };

    // Renderitzar la plantilla amb les dades
    res.render('civilizacion', data);
  } catch (err) {
    console.error(err);
    res.status(500).send('Error al consultar la tabla para obtener Civilizaciones');
  }
});

//Pantalla programadores
app.get('/programadores', async (req, res) => {
   // Legim els fitxers JSON
    const programadores = JSON.parse(
      fs.readFileSync(path.join(__dirname, 'data', 'programadores.json'), 'utf8')
  );

  // Preparem les dades per a la plantilla
    data = {
    programadores: programadores.programadores
  }

  // Renderitza la plantilla informe.hbs
  res.render('programadores', data);
});

app.get('/batallasEdit', async (req, res) => {
  try {
    const batallasID = parseInt(req.query.id, 10)

    if (!Number.isInteger(batallasID) || batallasID <= 0) {
      return res.status(400).send('Parámetro ID batalla inválido')
    }

    const batallasRows = await db.query(`
      select bs.civilization_id as ID_Civ,
        cs.name as Nombre, 
        bs.id_battle as ID_Bat,
        bs.wood_acquired as Madera_conseguida,
        bs.iron_acquired as Hierro_conseguido, 
        bs.civ_enem as ID_Civ_Enem,
        bl.log_entry as Log
        from battle_stats bs
        join battle_log bl on bs.civilization_id = bl.civilization_id
          and bs.id_battle = bl.id_battle
        join civilization_stats cs on bs.civilization_id = cs.civilization_id
        where bs.id_battle = ${batallasID}
        order by bs.id_battle,bs.civilization_id;
    `)

    if (!batallasRows || batallasRows.length === 0) {
      return res.status(404).send('Batalla no encontrada')
    }

    const batallasJson = db.table_to_json(batallasRows, {
  ID_Civ: 'number',
  Nombre: 'string',
  ID_Bat: 'number',
  Madera_conseguida: 'number',
  Hierro_conseguido: 'number',
  ID_Civ_Enem: 'number',
  Log: 'string'
});
    res.render('batallasEdit', {
      batallas: batallasJson
    })
  } catch (err) {
    console.error(err)
    res.status(500).send('Error consultando el detalle de la batalla')
  }
})



// Start server
const httpServer = app.listen(port, () => {
  console.log(`http://localhost:${port}`);
  console.log(`http://localhost:${port}/batallas`);
  console.log(`http://localhost:${port}/civilizacion`);
  console.log(`http://localhost:${port}/programadores`);
});

// Graceful shutdown
process.on('SIGINT', async () => {
  await db.end();
  httpServer.close();
  process.exit(0);
});