package org.lldm.xaltipac.service.security.model;

/**
 * Clase Modelo para constrolas los accesos.
 * @author Juan Mateo.
 *
 */

public class RolePriorityUrl {

    private String role;
    private String urlLoginSuccess;
    private Integer priority;

    public RolePriorityUrl(String role,String urlLoginSuccess, Integer priority){
        this.role = role;
        this.urlLoginSuccess = urlLoginSuccess;
        this.priority = priority;
    }

    /**
     * @return the _role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role the _role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the _urlLoginSuccess
     */
    public String getUrlLoginSuccess() {
        return urlLoginSuccess;
    }

    /**
     * @param urlLoginSuccess the _urlLoginSuccess to set
     */
    public void setUrlLoginSuccess(String urlLoginSuccess) {
        this.urlLoginSuccess = urlLoginSuccess;
    }

    /**
     * @return the _priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param priority the _priority to set
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
