package jpa;

import jakarta.persistence.*;

public class ESIRifyPersistence {
  private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("esirify");

  public static EntityManagerFactory getEMF() {
    return EMF;
  }

  public static void close() {
    EMF.close();
  }
}
