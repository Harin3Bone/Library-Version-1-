package gl_Repository;

import gl_Object.Librarian;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LibrarianList {
    private List<Librarian> librarians = new ArrayList<>();

    public List<Librarian> getLibrarians() {

        return librarians;
    }

    public void setLibrarians(List<Librarian> librarians) {

        this.librarians = librarians;
    }

    //************************** Librarian List **************************//
    public void DataLibrarianList (LibrarianList librarians){
        librarians.getLibrarians().add(new Librarian(UUID.randomUUID(),"Hidari","Nagano","1111","1111"));
        librarians.getLibrarians().add(new Librarian(UUID.randomUUID(),"Harin","Thananam","3333","3333"));
        librarians.getLibrarians().add(new Librarian(UUID.randomUUID(),"Miki","Nagano","2222","2222"));
    }

}
