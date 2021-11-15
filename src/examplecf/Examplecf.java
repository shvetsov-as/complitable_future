/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examplecf;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class Examplecf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

       
            ExecutorService executor = Executors.newFixedThreadPool(3);//sozdaem pool potokov

            ArrayList<CompletableFuture<String>> cflist = new ArrayList<>();

            for (int i = 1; i < 5; i++) {

                int l = i;

                CompletableFuture<String> cfs = CompletableFuture.supplyAsync(new Supplier<String>() {
                    @Override
                    public String get() {
                        System.out.println("Potok " + Thread.currentThread().getName());
                        return Tugodum.getResult(l);
                    }
                }, executor).thenApply(new Function<String, String>() {
                    @Override
                    public String apply(String t) {
                        System.out.println("Potok vernyl " + Thread.currentThread().getName() + " vernyl" + t);
                        return Thread.currentThread().getName() + ":" + t;
                    }
                }
                );
                cflist.add(cfs);
                if (cfs.isDone()) {
                    System.out.println("Zadanie " + i + " zaversheno");

                } else {
                    System.out.println("Zadanie " + i + " ne zaversheno");
                }
                 cfs.complete("Avariynoe zavershenie " + i);//Ybratb - prodoljet vbIpolnenie

            }
            
            for (int i = 1; i < 5; i++) {
                if (cflist.get(i-1).isDone()){
                    try {
                        System.out.println("Zadanie " + i + " zaversheno");
                        System.out.println("Vozvrasheno znachenie: " + cflist.get(i-1).get());
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Examplecf.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (ExecutionException ex) {
                        Logger.getLogger(Examplecf.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    System.out.println("Zadanie " + i + " ne zaversheno");
                }
                
               
            }

            executor.shutdown();

        }
    }

