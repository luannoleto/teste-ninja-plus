package models;

import java.io.File;
import java.util.List;

public class FilmeModel {
    public String titulo;
    public String status;
    public Integer ano;
    public String estreia;
    public List<String> atores;
    public String sinopse;
    public File capa;

    public FilmeModel(String titulo, String status, Integer ano, String estreia, List<String> atores, String sinopse, String capa) {
        this.titulo = titulo;
        this.status = status;
        this.ano = ano;
        this.estreia = estreia;
        this.atores = atores;
        this.sinopse = sinopse;
        this.capa = new File(this.capaPath() + capa);
    }

    private String capaPath() {
        String executionPath = System.getProperty("user.dir");
        String os = System.getProperty("os.name");
        String diretorioCapa;

        if (os.contains("windows")) {
            diretorioCapa = executionPath + "\\src\\main\\resources\\capaFilme\\";
        } else {
            diretorioCapa = executionPath + "/src/main/resources/capaFilme/";
        }
        return diretorioCapa;
    }
}
