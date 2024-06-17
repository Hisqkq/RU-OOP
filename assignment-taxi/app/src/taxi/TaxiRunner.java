package taxi;

public class TaxiRunner implements Runnable{
    private Taxi taxi;

    public TaxiRunner(Taxi taxi){
        this.taxi = taxi;
    }

    @Override
    public void run(){
        while(!Station.isClosed()){
            taxi.takePassengers();
        }

        // Edge case, thread reads waitingPassengers > 0 then enters leaveStation, then nrOfPassengersAtStation == 0 and is paused..
        while(Station.isClosed() && Station.waitingPassengers() > 0){
            taxi.takePassengers();
        }
        System.out.println("Taxi Thread closed");
    }
}