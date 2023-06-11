package ru.stqa.pft.sendbox;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.sandbox.Primes;

public class PrimeTests {

  @Test
  public void testPrime() {
    Assert.assertTrue(Primes.IsPrimeFast(Integer.MAX_VALUE));
  }

  @Test (enabled = false)
  public void testPrimeLong() {
    long n = Integer.MAX_VALUE;
    Assert.assertTrue(Primes.IsPrime(n));
  }

  @Test
  public void testNonPrimes() {
    Assert.assertFalse(Primes.IsPrime(Integer.MAX_VALUE - 2));
  }

}
