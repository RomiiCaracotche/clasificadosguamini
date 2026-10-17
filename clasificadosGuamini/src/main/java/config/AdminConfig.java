package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AdminConfig {

    private static String dniAdmin;

    @Value("${admin}")
    public void setDniAdmin(String dni) {
        AdminConfig.dniAdmin = dni;
    }

    public static boolean esAdmin(String dni) {
        return dniAdmin != null && dniAdmin.equals(dni);
    }
}
