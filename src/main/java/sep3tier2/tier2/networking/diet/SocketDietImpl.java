package sep3tier2.tier2.networking.diet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sep3tier2.tier2.models.*;
import sep3tier2.tier2.models.diet.*;
import sep3tier2.tier2.networking.ServerConnector;
import sep3tier2.tier2.services.SocketsUtilMethods;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * The sockets class for handling diet requests
 * @version 1.0
 * @author Group1
 */
@Component
public class SocketDietImpl implements SocketDiet
{
    private Gson gson;

    @Autowired
    ServerConnector serverConnector;

    @Autowired
    SocketsUtilMethods socketsUtilMethods;

    /**
     * No-argument constructor which initialized the gson object
     */
    public SocketDietImpl() {
        gson = new Gson();
    }

    @Override
    public int addDiet(DietWithOwner diet) throws Exception {
        Request request = new Request(ActionType.DIET_CREATE, diet);
        int dietId = socketsUtilMethods.requestWithIntegerReturnTypeWithoutImages(request);

        if(dietId == -1)
            throw new Exception("Could not create diet");

        return dietId;
    }

    @Override
    public DietWithOwner getDietById(int id) throws Exception {
        Request request = new Request(ActionType.DIET_GET_BY_ID, id);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if(response == null || response.getRequest() == null)
            throw new Exception("Could not retrieve diet with id "+ id);

        return gson.fromJson(response.getRequest().getArgument().toString(), DietWithOwner.class);
    }

    @Override
    public List<DietSVWithOwner> getPublicDiets(int offset) throws Exception {
        Request request = new Request(ActionType.DIET_GET_PUBLIC, offset);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if (response == null || response.getRequest() == null)
            throw new Exception("Could not retrieve public diets");

        Type dietListType = new TypeToken<List<DietSVWithOwner>>(){}.getType();
        return gson.fromJson(response.getRequest().getArgument().toString(), dietListType);
    }

    @Override
    public List<DietShortVersion> getPrivateDietsForUser(int userId, int offset) throws Exception {
        List<Integer> ints = new ArrayList<>();
        ints.add(userId);
        ints.add(offset);
        Request request = new Request(ActionType.DIET_GET_PRIVATE, ints);
        ActualRequest response = serverConnector.requestToServer(new ActualRequest(request, null));
        if (response == null || response.getRequest() == null)
            throw new Exception("Could not retrieve private diets");

        Type dietListType = new TypeToken<List<DietShortVersion>>(){}.getType();
        return gson.fromJson(response.getRequest().getArgument().toString(), dietListType);
    }

    @Override
    public void editDiet(Diet diet) throws Exception {
        boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(new Request(ActionType.DIET_EDIT, diet));
        if(!bool)
            throw new Exception("Could not edit diet with id " + diet.getId());
    }

    @Override
    public void deleteDiet(int id) throws Exception {
        boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(new Request(ActionType.DIET_DELETE, id));
        if(!bool)
            throw new Exception("Could not delete diet with id " + id);
    }

    @Override
    public int addMealToDiet(MealWithDiet meal) throws Exception {
        Request request = new Request(ActionType.DIET_ADD_MEAL, meal);
        int mealId = socketsUtilMethods.requestWithIntegerReturnTypeWithoutImages(request);

        if(mealId == -1)
            throw new Exception("Could not create meal");

        return mealId;
    }

    @Override
    public void editMealInDiet(MealWithDiet meal) throws Exception {
        boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(new Request(ActionType.DIET_EDIT_MEAL, meal));
        if(!bool)
            throw new Exception("Could not edit meal with id" + meal.getId());
    }

    @Override
    public void deleteMealFromDiet(int mealId, int dietId) throws Exception {
        List<Integer> ints = new ArrayList<>();
        ints.add(mealId);
        ints.add(dietId);
        boolean bool = socketsUtilMethods.requestWithBooleanReturnTypeWithoutImages(new Request(ActionType.DIET_DELETE_MEAL, ints));
        if(!bool)
            throw new Exception("Could not delete meal with id" + mealId);
    }
}
