import org.junit.Test;
import static org.junit.Assert.*;
public class SquadTest{
  @Test
   public void Squad_instantiatesCorrectly_true() {
     Squad mySquad = new Squad("Suicide", "Murder");
     assertEquals(true, mySquad instanceof Squad);
   }

   @Test
   public void Squad_getName_batman() {
     Squad mySquad = new Squad("Dark", "Murder");
     assertEquals("Dark", mySquad.getSquadName());
   }

   @Test
   public void Squad_getAge_55() {
     Squad mySquad = new Squad("Suicide", "Murder");
     assertEquals("Murder", mySquad.getCause());
   }

   @Test
   public void Squad_getId_0() {
     Squad mySquad = new Squad("Suicide", "Murder");
     assertEquals(2, mySquad.getId());
   }

  }
