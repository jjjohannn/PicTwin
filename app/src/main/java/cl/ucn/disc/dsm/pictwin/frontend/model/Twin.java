package cl.ucn.disc.dsm.pictwin.frontend.model;


public class Twin {

    private long id;
    private boolean disliked;
    private Pic myPic;
    private Pic yoursPic;

    public Pic getMyPic() {
        return myPic;
    }

    public Pic getYoursPic() {
        return yoursPic;
    }


    public long getId() {
        return id;
    }

    public boolean isDisliked() {
        return disliked;
    }

}
