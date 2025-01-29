package src;

import java.util.UUID;

public class UUIDGen {
  public static void main(String[] args) {
    for (int i = 0; i < 3; i++) {
      UUID uuid = UUID.randomUUID();
      System.out.println(uuid);
    }
  }
}