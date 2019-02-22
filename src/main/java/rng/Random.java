/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rng;

/**
 * An implementation of a Linear Congruential Generator
 * State is generated as (A * seed + increment) % modulus)
 * The algorithm relies on Long being 64-bits and Integer being 32-bit
 * @author sami
 */
public class Random {
    private long seed;
    private long multiplier;
    private long increment;
    private long modulus;
    
    public Random() {
        this(System.nanoTime());
    }
    
    /**
     * Initialize the PRGN using GLIBC LCG parameters
     * source: https://en.wikipedia.org/wiki/Linear_congruential_generator#Parameters_in_common_use
     * These values are basically just magic numbers that have suitable mathematical properties
     * @param seed A seed value to initialize the generator
     */
    public Random(long seed) {
        this.seed = seed;
        multiplier = 1103515245;
        increment = 12345;
        modulus = (long) Math.pow(2, 31);
    }
    
    /**
     * Generate the next seed and return it
     * @return 
     */
    private long nextSeed() {
        seed = (multiplier * seed + increment) % modulus;
        
        return seed;
    }
    
    /**
     * Generate a pseudorandom integer from the seed
     * @return an pseudorandom integer value 
     */
    public int nextInt() {
        // The top 34 bits are least random, we ignore them
        long s = nextSeed() << 34 >> 34;
        return (int) s;
    }
    
    /**
     * Generate an integer in range 0..n, zero is inclusive
     * @param n The value bound, the integer cannot be equal or higher than this
     * @return A pseudorandom number in range 0..n
     */
    public int nextInt(int n) {
        int s = (int) nextSeed();
        
        if (s < 0) s *= -1;
        
        return s % n;
    }
    
    /**
     * Since our PRGN is integer-based, generate an integer between 0 and max
     * and divide by the max to generate a double
     * @return A double value between 0..1
     */
    public double nextDouble() {
        return nextInt(Integer.MAX_VALUE) / (double) Integer.MAX_VALUE;
    }
    
    /**
     * Generate a boolean by looking at the sign-bit of the next integer
     * @return True or False
     */
    public boolean nextBoolean() {
        // When generating an integer, the sign can be negative or positive
        // We use this to our advantage by only looking at the sign bit
        return nextInt() >> 31 != 0;
    }
}
