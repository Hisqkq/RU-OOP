package taxi;

public class TrainRunner implements Runnable{
    private Train train;

    public TrainRunner(Train train){
        this.train = train;
    }

    @Override
    public void run(){
        while(train.getNrOfTrips() < Simulation.TRAIN_TRIPS){
            train.loadPassengers(Util.getRandomNumber(Simulation.MIN_TRAVELLERS, Simulation.MAX_TRAVELLERS));
            train.unloadPassengers();
        }

        train.closeStation();

        // while(!Station.isClosed()){
        //     train.closeStation();
        // }
        System.out.println("Train Thread closed");
    }
}
