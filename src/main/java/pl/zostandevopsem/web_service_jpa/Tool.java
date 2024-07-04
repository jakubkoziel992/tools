package pl.zostandevopsem.web_service_jpa;

import jakarta.persistence.*;

@Table(name = "tools")
@Entity
public class Tool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String toolName;
    private String description;
    private Integer startDate;

    public Tool() {
    }

    public Integer getId() {
        return id;
    }

    public String getToolName() {
        return toolName;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStartDate() {
        return startDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Integer startDate) {
        this.startDate = startDate;
    }
}
