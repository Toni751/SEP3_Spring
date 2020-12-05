package sep3tier2.tier2.models;

public enum ActionType
{
    USER_REGISTER,
    USER_LOGIN,
    USER_GET_BY_ID,
    USER_EDIT,
    USER_DELETE,
    USER_FRIEND_REQUEST_SEND,
    USER_FRIEND_REQUEST_RESPONSE,
    USER_FRIEND_REMOVE,
    USER_SHARE_TRAININGS,
    USER_SHARE_DIETS,
    USER_FOLLOW_PAGE,
    USER_RATE_PAGE,
    USER_REPORT,
    USER_DELETE_NOTIFICATION,
    USER_FILTER,
    USER_GET_GYMS,
    USER_GET_NOTIFICATIONS,
    USER_GET_FRIENDS,
    POST_CREATE,
    POST_GET_BY_ID,
    POST_GET_FOR_USER,
    POST_GET_BY_USER,
    POST_EDIT,
    POST_DELETE,
    POST_LIKE,
    POST_REPORT,
    POST_ADD_COMMENT,
    POST_DELETE_COMMENT,
    POST_GET_LIKES,
    POST_GET_COMMENTS,
    TRAINING_CREATE,
    TRAINING_GET_BY_ID,
    TRAINING_GET_PUBLIC,
    TRAINING_GET_PRIVATE,
    TRAINING_GET_USER,
    TRAINING_GET_WEEK,
    TRAINING_EDIT,
    TRAINING_DELETE,
    TRAINING_ADD_EXERCISE,
    TRAINING_EDIT_EXERCISE,
    TRAINING_DELETE_EXERCISE,
    ADMIN_GET_USERS,
    ADMIN_GET_POSTS,
    ADMIN_GET_NUMBER,
    HAS_IMAGES
}
