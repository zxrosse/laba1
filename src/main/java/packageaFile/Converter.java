package packageaFile;

import org.apache.log4j.Logger;

public class Converter {
    private static final Logger logger = Logger.getLogger(Converter.class);
    private ConversionType type;
    private String fromUnit;
    private String toUnit;

    private static final String[] LENGTH_UNITS = {"m", "km", "cm"};
    private static final String[] MASS_UNITS   = {"g", "kg"};

    public Converter(ConversionType type, String fromUnit, String toUnit) {
        this.type = type;
        this.fromUnit = fromUnit;
        this.toUnit = toUnit;
    }

    public double convert(double value) {
        logger.info("Начало конвертации: " + value + " " + fromUnit + " -> " + toUnit
                + " (" + type + ")");

        if (!isUnitSupported(type, fromUnit) || !isUnitSupported(type, toUnit)) {
            logger.error("Неподдерживаемая единица: from=" + fromUnit + ", to=" + toUnit);
            throw new IllegalArgumentException("Неподдерживаемая единица измерения");
        }

        double inBase = toBase(value, type, fromUnit);
        double result = fromBase(inBase, type, toUnit);

        logger.info("Результат конвертации: " + result + " " + toUnit);
        return result;
    }

    private boolean isUnitSupported(ConversionType type, String unit) {
        String[] units = (type == ConversionType.LENGTH) ? LENGTH_UNITS : MASS_UNITS;
        for (String u : units) {
            if (u.equalsIgnoreCase(unit)) {
                return true;
            }
        }
        return false;
    }

    private double toBase(double value, ConversionType type, String unit) {
        if (type == ConversionType.LENGTH) {
            switch (unit.toLowerCase()) {
                case "m":  return value;
                case "km": return value * 1000;
                case "cm": return value / 100;
                default:
                    throw new IllegalArgumentException("Неизвестная единица длины: " + unit);
            }
        } else {
            switch (unit.toLowerCase()) {
                case "g":  return value;
                case "kg": return value * 1000;
                default:
                    throw new IllegalArgumentException("Неизвестная единица массы: " + unit);
            }
        }
    }

    private double fromBase(double value, ConversionType type, String unit) {
        if (type == ConversionType.LENGTH) {
            switch (unit.toLowerCase()) {
                case "m":  return value;
                case "km": return value / 1000;
                case "cm": return value * 100;
                default:
                    throw new IllegalArgumentException("Неизвестная единица длины: " + unit);
            }
        } else {
            switch (unit.toLowerCase()) {
                case "g":  return value;
                case "kg": return value / 1000;
                default:
                    throw new IllegalArgumentException("Неизвестная единица массы: " + unit);
            }
        }
    }
}
