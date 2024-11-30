package softuni.exam.readFile;
//TestAttractionServiceReadFromFile

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import softuni.exam.service.impl.AttractionServiceImpl;

import java.io.IOException;

@ExtendWith(MockitoExtension.class)
public class TestAttractionServiceReadFromFile {

    @InjectMocks
    private AttractionServiceImpl attractionService;

    @Test
    void readAttractionsFromFile() throws IOException {

        String expected = """
                [
                  {
                    "name": "Machu Picchu",
                    "description": "An ancient Incan city located in the Andes mountains",
                    "type": "historical site",
                    "elevation": 2430,
                    "country": 134
                  },
                  {
                    "name": "Machu Picchu",
                    "description": "An ancient Incan city located in the Andes mountains",
                    "type": "historical site",
                    "elevation": 2430,
                    "country": 134
                  },
                  {
                    "name": "Christ the Redeemer",
                    "description": "A massive statue of Jesus Christ overlooking Rio de Janeiro",
                    "type": "monument",
                    "elevation": 710,
                    "country": 24
                  },
                  {
                    "name": "Iguazu Falls",
                    "description": "A large series of waterfalls",
                    "type": "natural wonder",
                    "elevation": 195,
                    "country": 7
                  },
                  {
                    "name": "Salar de Uyuni",
                    "description": "The world's largest salt flat",
                    "type": "natural wonder",
                    "elevation": 3656,
                    "country": 21
                  },
                  {
                    "name": "Galapagos Islands",
                    "description": "A unique volcanic archipelago known for diverse wildlife",
                    "type": "natural reserve",
                    "elevation": 0,
                    "country": 50
                  },
                  {
                    "name": "Atacama Desert",
                    "description": "The driest desert in the world",
                    "type": "desert",
                    "elevation": 2400,
                    "country": 35
                  },
                  {
                    "name": "Angel Falls",
                    "description": "The world's tallest uninterrupted waterfall",
                    "type": "waterfall",
                    "elevation": 979,
                    "country": 190
                  },
                  {
                    "name": "Torres del Paine",
                    "description": "A stunning national park in Patagonia with mountains, glaciers, and lakes",
                    "type": "national park",
                    "elevation": 2800,
                    "country": 35
                  },
                  {
                    "name": "La Sagrada Familia",
                    "description": "A stunning basilica in Bogota",
                    "type": "religious building",
                    "elevation": 2640,
                    "country": 37
                  },
                  {
                    "name": "Lake Titicaca",
                    "description": "The largest lake in South America",
                    "type": "lake",
                    "elevation": 3812,
                    "country": 21
                  },
                  {
                    "name": "Valley of the Moon",
                    "description": "A unique desert landscape with rock formations and sand dunes",
                    "type": "natural wonder",
                    "elevation": 2500,
                    "country": 35
                  },
                  {
                    "name": "Ciudad Perdida",
                    "description": "An ancient city in the mountains",
                    "type": "historical site",
                    "elevation": 1200,
                    "country": 37
                  },
                  {
                    "name": "Uyuni Train Cemetery",
                    "description": "An eerie collection of old train cars in the desert",
                    "type": "museum",
                    "elevation": 3665,
                    "country": 21
                  },
                  {
                    "name": "Canoa Quebrada",
                    "description": "A beach with red sand cliffs",
                    "type": "beach",
                    "elevation": 0,
                    "country": 24
                  },
                  {
                    "name": "Perito Moreno Glacier",
                    "description": "A massive advancing glacier in Patagonia",
                    "type": "glacier",
                    "elevation": 1500,
                    "country": 7
                  },
                  {
                    "name": "Teotihuacan",
                    "description": "An ancient Mesoamerican city with large pyramids",
                    "type": "archaeological site",
                    "elevation": 2300,
                    "country": 109
                  },
                  {
                    "name": "Tikal",
                    "description": "A large Mayan archaeological site",
                    "type": "archaeological site",
                    "elevation": 300,
                    "country": 68
                  },
                  {
                    "name": "Cotopaxi",
                    "description": "A majestic active volcano",
                    "type": "volcano",
                    "elevation": 5897,
                    "country": 50
                  },
                  {
                    "name": "Copacabana Beach",
                    "description": "A famous beach in Rio de Janeiro",
                    "type": "beach",
                    "elevation": 0,
                    "country": 24
                  },
                  {
                    "name": "Rapa Nui",
                    "description": "The iconic Moai statues of Easter Island",
                    "type": "archaeological site",
                    "elevation": 0,
                    "country": 35
                  },
                  {
                    "name": "Itaipu Dam",
                    "description": "One of the largest hydroelectric dams in the world",
                    "type": "hydroelectric dam",
                    "elevation": 220,
                    "country": 24
                  },
                  {
                    "name": "Aconcagua",
                    "description": "The highest mountain in the Americas, located in the Andes",
                    "type": "mountain",
                    "elevation": 6960,
                    "country": 7
                  },
                  {
                    "name": "Nazca Lines",
                    "description": "Ancient geoglyphs etched into the southern desert",
                    "type": "archaeological site",
                    "elevation": 520,
                    "country": 134
                  },
                  {
                    "name": "Caracol",
                    "description": "An ancient Mayan city located in the jungles",
                    "type": "archaeological site",
                    "elevation": 500,
                    "country": 18
                  },
                  {
                    "name": "San Blas Islands",
                    "description": "A group of islands known for their pristine beaches and indigenous culture",
                    "type": "beach",
                    "elevation": 0,
                    "country": 131
                  },
                  {
                    "name": "Monteverde Cloud Forest",
                    "description": "A tropical cloud forest reserve",
                    "type": "nature reserve",
                    "elevation": 1600,
                    "country": 41
                  },
                  {
                    "name": "Cayos Cochinos",
                    "description": "A group of small islands off the northern coast",
                    "type": "beach",
                    "elevation": 0,
                    "country": 73
                  },
                  {
                    "name": "Lake Atitlan",
                    "description": "A beautiful crater lake surrounded by volcanoes",
                    "type": "lake",
                    "elevation": 1560,
                    "country": 68
                  },
                  {
                    "name": "Salt Cathedral of Zipaquira",
                    "description": "An underground Roman Catholic church built within the tunnels of a salt mine",
                    "type": "religious building",
                    "elevation": 2650,
                    "country": 37
                  },
                  {
                    "name": "El Yunque National Forest",
                    "description": "A tropical rainforest",
                    "type": "national park",
                    "elevation": 1050,
                    "country": 138
                  },
                  {
                    "name": "Tarapoto",
                    "description": "A scenic region known for waterfalls and rainforests",
                    "type": "natural wonder",
                    "elevation": 350,
                    "country": 134
                  },
                  {
                    "name": "La Guaira",
                    "description": "A beautiful coastal city with a rich colonial history",
                    "type": "city",
                    "elevation": 0,
                    "country": 190
                  },
                  {
                    "name": "Ruins of Copan",
                    "description": "An ancient Mayan site known for its stunning sculptures",
                    "type": "archaeological site",
                    "elevation": 600,
                    "country": 73
                  },
                  {
                    "name": "The Pink Sea",
                    "description": "A unique phenomenon where a saltwater lake turns pink",
                    "type": "natural wonder",
                    "elevation": 0,
                    "country": 37
                  },
                  {
                    "name": "Poas Volcano",
                    "description": "An active volcano with a massive crater lake",
                    "type": "volcano",
                    "elevation": 2708,
                    "country": 41
                  },
                  {
                    "name": "Mount Roraima",
                    "description": "A flat-topped mountain on the border between three countries",
                    "type": "mountain",
                    "elevation": 2810,
                    "country": 190
                  },
                  {
                    "name": "La Mano de Punta del Este",
                    "description": "A giant sculpture of a hand emerging from the sand on a beach",
                    "type": "sculpture",
                    "elevation": 0,
                    "country": 186
                  },
                  {
                    "name": "Los Glaciares National Park",
                    "description": "A UNESCO World Heritage Site known for its stunning glaciers",
                    "type": "national park",
                    "elevation": 200,
                    "country": 7
                  },
                  {
                    "name": "Los Roques Archipelago",
                    "description": "A stunning coral archipelago and national park off the coast",
                    "type": "national park",
                    "elevation": 0,
                    "country": 190
                  },
                  {
                    "name": "Caral",
                    "description": "One of the oldest civilizations in the Americas, an ancient city",
                    "type": "archaeological site",
                    "elevation": 350,
                    "country": 134
                  },
                  {
                    "name": "San Rafael Waterfall",
                    "description": "The tallest waterfall in Ecuador, located in the Amazon rainforest",
                    "type": "waterfall",
                    "elevation": 150,
                    "country": 50
                  },
                  {
                    "name": "Fernando de Noronha",
                    "description": "A remote archipelago and UNESCO World Heritage site known for its biodiversity",
                    "type": "natural reserve",
                    "elevation": 0,
                    "country": 24
                  },
                  {
                    "name": "Cerro de los Siete Colores",
                    "description": "A mountain in Argentina with a striking array of colored stripes",
                    "type": "natural wonder",
                    "elevation": 4175,
                    "country": 7
                  },
                  {
                    "name": "Chimborazo",
                    "description": "The highest mountain in Ecuador, with a peak that is the farthest point from the Earth's center",
                    "type": "mountain",
                    "elevation": 6263,
                    "country": 50
                  },
                  {
                    "name": "Rio Secreto",
                    "description": "An underground river and cave system in the Yucatan Peninsula",
                    "type": "natural wonder",
                    "elevation": 0,
                    "country": 109
                  },
                  {
                    "name": "Marieta Islands",
                    "description": "A group of small, uninhabited islands off the coast of Mexico, known for their hidden beach",
                    "type": "beach",
                    "elevation": 0,
                    "country": 109
                  },
                  {
                    "name": "Pantanal",
                    "description": "The world's largest tropical wetland",
                    "type": "wetland",
                    "elevation": 100,
                    "country": 24
                  },
                  {
                    "name": "Punta Tombo",
                    "description": "A wildlife reserve in Argentina famous for its large colony of Magellanic penguins",
                    "type": "wildlife reserve",
                    "elevation": 0,
                    "country": 7
                  },
                  {
                    "name": "Laguna Colorada",
                    "description": "A shallow salt lake in Bolivia famous for its red water and flamingos",
                    "type": "lake",
                    "elevation": 4278,
                    "country": 21
                  },
                  {
                    "name": "Salt Flats of Maras",
                    "description": "A complex of salt evaporation ponds dating back to Incan times",
                    "type": "historical site",
                    "elevation": 3000,
                    "country": 134
                  }
                ]""";

        String actual = attractionService.readAttractionsFileContent()
                .replaceAll("\\r\\n|\\r|\\n", "\n").trim();
        Assertions.assertEquals(expected, actual);
    }
}