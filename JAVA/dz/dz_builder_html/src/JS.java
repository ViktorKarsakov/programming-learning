class JS {
    private String jsLink;

    public JS(String jsLink) {
        this.jsLink = jsLink;
    }

    @Override
    public String toString() {
        return "<script src=\"" + jsLink + "\"></script>";
    }
}
