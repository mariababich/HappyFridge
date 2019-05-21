package ua.iasa.happyfridge.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "files")
@Data
public class File {

    @Id
    private Long id;
    @Lob
    private byte[] file;
}
