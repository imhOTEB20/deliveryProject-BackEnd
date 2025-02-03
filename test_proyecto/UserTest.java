import com.molesgroup.deliveryproject.model.enums.UserType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTest {

    @Test
    public void testUser Creation() {
        // Arrange
        User user = new User();
        user.setAuth0Id("auth0|123456");
        user.setEmail("test@example.com");
        user.setUser Type(UserType.ADMIN); // Asumiendo que ADMIN es un valor de UserType

        // Act
        Long id = user.getId();
        String auth0Id = user.getAuth0Id();
        String email = user.getEmail();
        UserType userType = user.getUser Type();

        // Assert
        assertThat(auth0Id).isEqualTo("auth0|123456");
        assertThat(email).isEqualTo("test@example.com");
        assertThat(userType).isEqualTo(UserType.ADMIN);
        assertThat(id).isNull(); // El ID debe ser nulo ya que no se ha persistido
    }

    @Test
    public void testUser TypeEnum() {
        // Arrange
        User user = new User();
        user.setUser Type(UserType.USER); // Asumiendo que USER es un valor de UserType

        // Act
        UserType userType = user.getUser Type();

        // Assert
        assertThat(userType).isEqualTo(UserType.USER);
    }

    @Test
    public void testUser IdGeneration() {
        // Arrange
        User user = new User();
        user.setAuth0Id("auth0|654321");
        user.setEmail("test2@example.com");
        user.setUser Type(UserType.MODERATOR); // Asumiendo que MODERATOR es un valor de UserType

        // Act
        // Simulamos la persistencia y generación de ID
        user.setId(1L); // Simulamos que el ID fue generado

        // Assert
        assertThat(user.getId()).isEqualTo(1L);
    }
}