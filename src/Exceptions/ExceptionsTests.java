package Exceptions;

import Enums.Arrangement;
import Enums.Edge;
import Enums.Shape;
import grid.Grid;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ResourceBundle;


class ExceptionsTests {
     private static final String FILE_CONFIG_LABEL = "CSVFileName";
     private static final String NEIGHBORHOD_CONFIG_LABEL = "NeighborhoodType";
     private static final String CELLSHAPE_CONFIG_LABEL = "CellShape";
     private static final String EDGE_CONFIG_LABEL = "EdgePolicies";
    private static final String DEFAULT_RESOURCE_PACKAGE = "/Resources/";
    private static final String CONFIGURATION_FILE = "BadExample";

    private String myFileName;
    private Grid myGrid;
    private ResourceBundle myConfiguration;
    private Shape myShape;
    private Arrangement neighborhoodType;
    private Edge edgePolicy;

     @BeforeEach
     void setup() {
         myConfiguration = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + CONFIGURATION_FILE);
         myFileName = myConfiguration.getString(FILE_CONFIG_LABEL);
         neighborhoodType = Arrangement.valueOf(myConfiguration.getString(NEIGHBORHOD_CONFIG_LABEL).toUpperCase());
         myShape = Shape.valueOf(myConfiguration.getString(CELLSHAPE_CONFIG_LABEL).toUpperCase());
         edgePolicy = Edge.valueOf(myConfiguration.getString(EDGE_CONFIG_LABEL).toUpperCase());

     }

     @Test
     void checkWidthAndHeight() {
         myGrid = new Grid(myFileName, neighborhoodType, myShape, edgePolicy);
         assertThrows(IndexOutOfBoundsException(), );
     }

     @Test
     void insertWrongPropertiesFileSetsDefault() {
         assertThrows(InvalidValueException(), );
     }


    @Test
    void insertNonexistentCsvFileSetsDefault() {
        assertThrows(InvalidValueException(), );
    }

 }
