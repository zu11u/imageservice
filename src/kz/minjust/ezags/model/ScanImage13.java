package kz.minjust.ezags.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="import_scans", schema="ZAGS_2013")
public class ScanImage13 extends ScanImage implements Serializable
{
    @Id
    @Column(name="zags_id")
    private String zagsId;
    @Column(name="image1")
    private byte[] image1;
    @Column(name="image2")
    private byte[] image2;

    public byte[] getImage1()
    {
        return this.image1;
    }

    public byte[] getImage2()
    {
        return this.image2;
    }

}
