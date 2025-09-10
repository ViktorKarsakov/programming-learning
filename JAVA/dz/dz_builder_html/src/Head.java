class Head {
    private String title;

    public Head(String title){
        this.title = title;
    }

    @Override
    public String toString() {
        return "<head><title>" + title + "</title></head>";
    }
}
