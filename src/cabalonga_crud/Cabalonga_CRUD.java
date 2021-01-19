package cabalonga_crud;

class Cabalonga_CRUD {

 
    public static void main(String[] args) {
        View_Home vH = new View_Home ();
        View_Search vS = new View_Search ();
        View_Add vA = new View_Add ();
        View_update vU = new View_update ();
        new Controller(vH, vS, vA, vU);
        vH.setTitle("Research Archive");
        vH.setVisible(true);
    
        
    }
    
}
