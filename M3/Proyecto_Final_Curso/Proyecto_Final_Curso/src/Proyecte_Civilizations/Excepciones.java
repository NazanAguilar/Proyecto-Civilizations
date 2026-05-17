package Proyecte_Civilizations;


class BuildingException extends Exception{
	BuildingException(){
		System.out.println("Error BuildingException");
	}
	BuildingException(String mensaje){
		System.out.println(mensaje);
	}
}

class ResourceException extends Exception{
	ResourceException(){
		System.out.println("ERROR RESOURCEEXCEPTION");
	}
	ResourceException(String mensaje){
		System.out.println(mensaje);
	}
}