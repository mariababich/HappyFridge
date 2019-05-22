package ua.iasa.happyfridge.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "files")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String fileName;
    private Long length;
    @Lob
    private byte[] file;
}
