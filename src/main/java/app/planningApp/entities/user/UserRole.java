package app.planningApp.entities.user;

public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    private final String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
