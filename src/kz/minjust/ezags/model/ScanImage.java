package kz.minjust.ezags.model;

public abstract class ScanImage
{
    private String zagsId;
    private byte[] image1;
    private byte[] image2;

    public abstract byte[] getImage1();
    public abstract byte[] getImage2();
}
