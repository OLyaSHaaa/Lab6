package ticket;

/**
 * Реализация интерфеса CoordinatesValidator
 */
public class CoordinatesValidatorImpl implements CoordinatesValidator{

    @Override
    public boolean validateCoordinatesX(Integer x) {
        return x != null && x > -27;
    }

    @Override
    public boolean validateCoordinatesY(double y) {
        return y > -279;
    }
}
