package ticket;

import exceptions.InvalidCoordinatesException;
import exceptions.InvalidCoordinatesXException;
import exceptions.InvalidCoordinatesYException;
import exceptions.InvalidFieldException;
import messages.Messenger;

/**
 * Реализация интерфеса CoordinatesBuilder
 */
public class CoordinatesBuilderImpl implements CoordinatesBuilder{
    private Integer x; //Значение поля должно быть больше -27, Поле не может быть null
    private double y; //Значение поля должно быть больше -279
    private CoordinatesValidator validator;

    public CoordinatesBuilderImpl(){
        validator = new CoordinatesValidatorImpl();
    }

    @Override
    public void setX(Integer x) throws InvalidCoordinatesXException {
        if (validator.validateCoordinatesX(x)) {
            this.x = x;
        } else {
            throw new InvalidCoordinatesXException();
        }
    }

    @Override
    public void setY(double y) throws InvalidCoordinatesYException {
        if (validator.validateCoordinatesY(y)) {
            this.y = y;
        } else {
            throw new InvalidCoordinatesYException();
        }
    }

    @Override
    public Coordinates getCoordinates(){
        return new Coordinates(x, y);
    }
}
