package simplex.bondpositionsystem.model;

public interface PositionManagementService {

    void registerPosition(String text) throws UserInputException;

    void deletePosition(String code) throws UserInputException;

}
