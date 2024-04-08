package Entity;

public class Especialidad
{
    private int id_especialidad;
    private String name;
    private String description;

    public Especialidad() {
    }

    public Especialidad(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public int getId_especialidad() {
        return id_especialidad;
    }

    public void setId_especialidad(int id_especialidad) {
        this.id_especialidad = id_especialidad;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Especialidad --> " +
                "id_especialidad: " + id_especialidad +
                " name: " + name +
                " description: " + description;
    }
}
