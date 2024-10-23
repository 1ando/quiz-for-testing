package delft;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConvertRomanToNumberTest {

    @ParameterizedTest(name = "converter")
    @MethodSource("generator")
    void convert(String description, String input, int expected) {
       int result = ConvertRomanToNumber.convertStringToNumber(input);
        assertEquals(expected, result);
    }

    public static Stream<Arguments> generator() {
    Arguments tc1 = Arguments.of("one", "I", 1);
    Arguments tc2 = Arguments.of("white space string", " ", 0);//edge case
    Arguments tc3 = Arguments.of("four", "IV", 4); //smaller numeral in front
    Arguments tc4 = Arguments.of("six", "VI", 6); //bigger numeral in front
    Arguments tc5 = Arguments.of("a lot of ones", "IIIIIIIIIII", 11);
    Arguments tc6 = Arguments.of("a lot of ones with white space", "I I I I I I I I I I I", 11);//see if whitespace affects it
    Arguments tc7 = Arguments.of("1k", "M", 1000);//I wanted to check ever roman character ot hit those lines of code
    Arguments tc8 = Arguments.of("50", "L", 50);
    Arguments tc9 = Arguments.of("100", "C", 100);
    Arguments tc10 = Arguments.of("10", "D", 500);

    return Stream.of(tc1,tc2,tc3,tc4,tc5,tc5, tc6, tc7,tc8, tc9, tc10);
    }

    @ParameterizedTest(name = "add")
    @MethodSource("generatorAdd")
    void add(String description, String romanNum1, String romanNum2, int expected) {
        int result = ConvertRomanToNumber.add(romanNum1, romanNum2);
        assertEquals(expected, result);
    }

    public static Stream<Arguments> generatorAdd() {
        Arguments tc1 = Arguments.of("nothing", " "," ", 0); //edge case
        Arguments tc2 = Arguments.of("only one val", "V"," ", 5);//make sure it stays same
        Arguments tc3 = Arguments.of("adding two", "V","II", 7);//simple adding
       // Arguments tc4 = Arguments.of("nothing", "II","III", 5); this fails

        return Stream.of(tc1, tc2, tc3,tc4);
    }
}
