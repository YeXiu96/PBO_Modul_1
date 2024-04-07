package kendaraan.khusus;

import kendaraan.Kendaraan;
import kendaraan.util.Flyable;

public  class Pesawat extends Kendaraan implements Flyable {
    @Override
    public void Start() {
        System.out.println("Menyalakan pesawat ");
    }

    @Override
    public void Stop() {
        System.out.println("Mematikan mesin pesawat ");
    }

    @Override
    public void Brake() {
        System.out.println("Pesawat melakukan pengereman darurat");
    }

    @Override
    public void fly() {
        System.out.println("Pesawat  lepas landas");
    }
}
