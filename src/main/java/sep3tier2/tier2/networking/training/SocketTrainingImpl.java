package sep3tier2.tier2.networking.training;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.networking.ServerConnector;
import sep3tier2.tier2.services.SocketsUtilMethods;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class SocketTrainingImpl implements SocketTraining
{
    private Gson gson;

    @Autowired
    ServerConnector serverConnector;

    @Autowired
    SocketsUtilMethods socketsUtilMethods;

    public SocketTrainingImpl() {
        gson = new Gson();
    }

    @Override
    public int addTraining(TrainingWithOwner training) throws Exception {
        Request request = new Request(ActionType.TRAINING_CREATE, training);
        int trainingId = socketsUtilMethods.requestWithIntegerReturnTypeWithoutImages(request);

        if(trainingId == -1)
            throw new Exception("Could not create training");

        return trainingId;
    }

    @Override
    public TrainingWithOwner getTrainingById(int id) throws Exception {
        Request request = new Request(ActionType.TRAINING_GET_BY_ID, id);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if(response == null || response.getRequest() == null)
            throw new Exception("Could not retrieve training with id " + id);

        return gson.fromJson(response.getRequest().getArgument().toString(), TrainingWithOwner.class);
    }

    @Override
    public List<TrainingSVWithOwner> getPublicTrainings(int offset) throws Exception {
        Request request = new Request(ActionType.TRAINING_GET_PUBLIC, offset);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if (response.getRequest().getArgument() == null)
            throw new Exception("Could not retrieve public trainings");

        Type trainingListType = new TypeToken<List<TrainingSVWithOwner>>(){}.getType();
        return gson.fromJson(response.getRequest().getArgument().toString(), trainingListType);
    }

    @Override
    public List<TrainingShortVersion> getPrivateTrainingsForUser(int userId, int offset) throws Exception {
        List<Integer> ints = new ArrayList<>();
        ints.add(userId);
        ints.add(offset);
        Request request = new Request(ActionType.TRAINING_GET_PRIVATE, ints);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if (response.getRequest().getArgument() == null)
            throw new Exception("Could not retrieve private trainings");

        Type trainingListType = new TypeToken<List<TrainingShortVersion>>(){}.getType();
        return gson.fromJson(response.getRequest().getArgument().toString(), trainingListType);
    }

    @Override
    public List<TrainingSVWithOwner> getTrainingsForUser(int id, int offset) throws Exception {
        List<Integer> ints = new ArrayList<>();
        ints.add(id);
        ints.add(offset);
        Request request = new Request(ActionType.TRAINING_GET_USER, ints);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if (response.getRequest().getArgument() == null)
            throw new Exception("Could not retrieve trainings for user " + id);

        Type trainingListType = new TypeToken<List<TrainingSVWithOwner>>(){}.getType();
        return gson.fromJson(response.getRequest().getArgument().toString(), trainingListType);
    }

    @Override
    public List<TrainingSVWithTime> getTrainingsInWeekForUser(int userId, int weekNumber) throws Exception {
        List<Integer> ints = new ArrayList<>();
        ints.add(userId);
        ints.add(weekNumber);
        Request request = new Request(ActionType.TRAINING_GET_WEEK, ints);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if (response == null || response.getRequest() == null)
            throw new Exception("Could not get trainings in week " + weekNumber + " for user " + userId);

        Type trainingsListType = new TypeToken<List<TrainingSVWithTime>>(){}.getType();
        return gson.fromJson(response.getRequest().getArgument().toString(), trainingsListType);
    }

    @Override
    public void editTraining(Training training) throws Exception {
        boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(new Request(ActionType.TRAINING_EDIT, training));
        if(!bool)
            throw new Exception("Could not edit training with id" + training.getId());
    }

    @Override
    public void deleteTraining(int id) throws Exception {
        boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(new Request(ActionType.TRAINING_DELETE, id));
        if(!bool)
            throw new Exception("Could not delete training with id" + id);
    }

    @Override
    public int addExerciseToTraining(ExerciseWithTraining exercise) throws Exception {
        Request request = new Request(ActionType.TRAINING_ADD_EXERCISE, exercise);
        int exerciseId = socketsUtilMethods.requestWithIntegerReturnTypeWithoutImages(request);

        if(exerciseId == -1)
            throw new Exception("Could not create exercise");

        return exerciseId;
    }

    @Override
    public void editExerciseInTraining(ExerciseWithTraining exercise) throws Exception {
        boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(new Request(ActionType.TRAINING_EDIT_EXERCISE, exercise));
        if(!bool)
            throw new Exception("Could not edit exercise with id" + exercise.getId());
    }

    @Override
    public void deleteExerciseFromTraining(int exerciseId, int trainingId) throws Exception {
        List<Integer> ints = new ArrayList<>();
        ints.add(exerciseId);
        ints.add(trainingId);
        boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(new Request(ActionType.TRAINING_DELETE_EXERCISE, ints));
        if(!bool)
            throw new Exception("Could not delete exercise with id" + exerciseId);
    }
}
