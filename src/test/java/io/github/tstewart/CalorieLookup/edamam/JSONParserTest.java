package io.github.tstewart.CalorieLookup.edamam;

import io.github.tstewart.CalorieLookup.Food;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class JSONParserTest {
    @Test
    public void JSONParsedSuccessfully() throws JSONException {
        //Arrange
        final String exampleResponse = "{\"_links\":{\"next\":{\"href\":\"https://api.edamam.com/api/food-database/parser?session=40&app_id=da5ccccd&app_key=830de1530183470f64e9ef0f6352421e&ingr=apple\",\"title\":\"Next page\"}},\"hints\":[{\"measures\":[{\"qualified\":[[{\"label\":\"small\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Qualifier_small\"}],[{\"label\":\"medium\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Qualifier_medium\"}],[{\"label\":\"large\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Qualifier_large\"}]],\"label\":\"Whole\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_unit\"},{\"label\":\"Serving\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_serving\"},{\"label\":\"Apple\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_apple\"},{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"},{\"qualified\":[[{\"label\":\"quartered\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Qualifier_quartered\"},{\"label\":\"chopped\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Qualifier_chopped\"}]],\"label\":\"Cup\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_cup\"}],\"food\":{\"foodId\":\"food_a1gb9ubb72c7snbuxr3weagwv0dd\",\"categoryLabel\":\"food\",\"label\":\"apple\",\"category\":\"Generic foods\",\"nutrients\":{\"PROCNT\":0.26,\"ENERC_KCAL\":52,\"FAT\":0.17,\"CHOCDF\":13.81,\"FIBTG\":2.4}}},{\"measures\":[{\"label\":\"Serving\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_serving\"},{\"label\":\"Pouch\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pouch\"},{\"label\":\"Package\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_package\"},{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"image\":\"https://www.edamam.com/food-img/771/7715ef0a0acc8062e37a3a19e6caa5a5.png\",\"foodContentsLabel\":\"ORGANIC APPLES\",\"foodId\":\"food_bwcqj4qbdtgvhkbb1ou52bablehf\",\"categoryLabel\":\"food\",\"label\":\"Ella's Kitchen Apples Apples Apples\",\"category\":\"Packaged foods\",\"brand\":\"Ella's Kitchen\",\"nutrients\":{\"PROCNT\":1.4285714285714286,\"ENERC_KCAL\":42.85714285714286,\"CHOCDF\":11.428571428571429,\"FIBTG\":1.4285714285714286}}},{\"measures\":[{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"image\":\"https://www.edamam.com/food-img/9f5/9f54a8bbadc9febf4d71bc96698ab803.jpg\",\"foodContentsLabel\":\"Apple\",\"foodId\":\"food_be9kxv3bo7sobaakr2rceautpjp2\",\"categoryLabel\":\"food\",\"label\":\"Apple Country  Apples\",\"category\":\"Packaged foods\",\"brand\":\"Apple Country\",\"nutrients\":{\"PROCNT\":0.03899999856948853,\"ENERC_KCAL\":7.7,\"FAT\":0.025,\"CHOCDF\":2.0579999923706054,\"FIBTG\":0.35999999046325687}}},{\"measures\":[{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"Apple\",\"foodId\":\"food_aqgwowob88ca3wbc8oxd6aol3rfk\",\"categoryLabel\":\"food\",\"label\":\"Apple Country, Apples\",\"category\":\"Packaged foods\",\"brand\":\"Apple Country\",\"nutrients\":{\"ENERC_KCAL\":51.94805194805195,\"CHOCDF\":14.29220794083236,\"FIBTG\":3.1818182437450853}}},{\"measures\":[{\"label\":\"Whole\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_unit\"},{\"label\":\"Serving\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_serving\"},{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"walnuts; pecans; brown sugar; kosher salt; ground cinnamon; cardamom; rolled oats; butter; apples; apple cider; vanilla ice cream\",\"foodId\":\"food_b8rrwcpbvba3asaru2mdsbagsbs2\",\"categoryLabel\":\"meal\",\"label\":\"Apple-Crisp Baked Apples\",\"category\":\"Generic meals\",\"nutrients\":{\"PROCNT\":0.9362503447706831,\"ENERC_KCAL\":106.03956616796349,\"FAT\":6.077002847401216,\"CHOCDF\":13.683948357131875,\"FIBTG\":2.1664565256307085}}},{\"measures\":[{\"label\":\"Whole\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_unit\"},{\"label\":\"Serving\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_serving\"},{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"Apples; Dark Brown Sugar; ground cinnamon; oats; all purpose flour; unsalted butter; kosher salt; orange; orange juice; honey\",\"foodId\":\"food_bcruvycav1jponay8eu0oa5fkbhx\",\"categoryLabel\":\"meal\",\"label\":\"Apple Pie Baked Apples\",\"category\":\"Generic meals\",\"nutrients\":{\"PROCNT\":1.1491020469471132,\"ENERC_KCAL\":134.5233856921379,\"FAT\":3.202887624039124,\"CHOCDF\":27.022139090138158,\"FIBTG\":2.160775443025757}}},{\"measures\":[{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"Apples\",\"foodId\":\"food_b52qyvfazt0m3abdstnp4a3dkvtl\",\"categoryLabel\":\"food\",\"label\":\"Apples\",\"category\":\"Packaged foods\",\"brand\":\"Mountaineer\",\"nutrients\":{\"ENERC_KCAL\":51.94805194805195,\"CHOCDF\":14.29220794083236,\"FIBTG\":3.1818182437450853}}},{\"measures\":[{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"Apples\",\"foodId\":\"food_bj030ujaizsbxcbik17jzblz8gmz\",\"categoryLabel\":\"food\",\"label\":\"Apples\",\"category\":\"Packaged foods\",\"brand\":\"Apple Country\",\"nutrients\":{\"ENERC_KCAL\":51.94805194805195,\"CHOCDF\":14.29220794083236,\"FIBTG\":3.1818182437450853}}},{\"measures\":[{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"Apples\",\"foodId\":\"food_bjgna80bjia37ravs39oratd4t4z\",\"categoryLabel\":\"food\",\"label\":\"Apples\",\"category\":\"Packaged foods\",\"brand\":\"Pinkids\",\"nutrients\":{\"ENERC_KCAL\":51.94805194805195,\"CHOCDF\":14.29220794083236,\"FIBTG\":3.1818182437450853}}},{\"measures\":[{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"Apples\",\"foodId\":\"food_ac5wwmobhru47ra2zum6la1zdnln\",\"categoryLabel\":\"food\",\"label\":\"Apples\",\"category\":\"Packaged foods\",\"brand\":\"Apple Wedge\",\"nutrients\":{\"PROCNT\":0.4090909130317121,\"ENERC_KCAL\":54.13223140495868,\"FAT\":0.4090909130317121,\"CHOCDF\":14.049586776859504,\"FIBTG\":2.107437977120896}}},{\"measures\":[{\"label\":\"Whole\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_unit\"},{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"APPLES.\",\"foodId\":\"food_ayz2lr9br5crdfbh9us56br4ledg\",\"categoryLabel\":\"food\",\"label\":\"APPLES\",\"category\":\"Packaged foods\",\"brand\":\"Meijer, Inc.\",\"nutrients\":{\"PROCNT\":0.3199999928474426,\"ENERC_KCAL\":52,\"CHOCDF\":14.289999961853027,\"FIBTG\":3.200000047683716}}},{\"measures\":[{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"image\":\"https://www.edamam.com/food-img/952/95283d2e88ddde6da732380d846e33c7.jpg\",\"foodContentsLabel\":\"Apple\",\"foodId\":\"food_awtilwybcoi3khbmhj5zja17u6x5\",\"categoryLabel\":\"food\",\"label\":\"Apple\",\"category\":\"Packaged foods\",\"brand\":\"Apples\",\"nutrients\":{\"ENERC_KCAL\":53.33333333333333,\"CHOCDF\":14.666666666666666,\"FIBTG\":3.333333333333333}}},{\"measures\":[{\"label\":\"Whole\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_unit\"},{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"APPLES.\",\"foodId\":\"food_azfjg86bzezfhqapgb1f5alqp3rm\",\"categoryLabel\":\"food\",\"label\":\"APPLES\",\"category\":\"Packaged foods\",\"brand\":\"Big Y Foods, Inc.\",\"nutrients\":{\"ENERC_KCAL\":52,\"CHOCDF\":14.289999961853027,\"FIBTG\":3.200000047683716}}},{\"measures\":[{\"label\":\"Piece\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_piece\"},{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"image\":\"https://www.edamam.com/food-img/5c7/5c794bc2ce9f4e5f1e3d4a3db2a877e5.jpg\",\"foodContentsLabel\":\"DRIED APPLES (APPLES; SODIUM METABISULFITE AND SODIUM SULFITE (TO PRESERVE COLOR)).\",\"foodId\":\"food_aa1c3embbqxdhna96tmdwawusiwr\",\"categoryLabel\":\"food\",\"label\":\"APPLES\",\"category\":\"Packaged foods\",\"brand\":\"Wal-Mart Stores, Inc.\",\"nutrients\":{\"ENERC_KCAL\":300,\"CHOCDF\":72.5,\"FIBTG\":7.5}}},{\"measures\":[{\"label\":\"Piece\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_piece\"},{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"image\":\"https://www.edamam.com/food-img/a0a/a0a262c2e829899aec07687bafb2187a.jpg\",\"foodContentsLabel\":\"APPLES; SULPHUR DIOXIDE.\",\"foodId\":\"food_beqqn49apz7xyzbrp0d9lb1fagis\",\"categoryLabel\":\"food\",\"label\":\"APPLES\",\"category\":\"Packaged foods\",\"brand\":\"Hy-Vee, Inc.\",\"nutrients\":{\"ENERC_KCAL\":250,\"FAT\":2.5,\"CHOCDF\":65,\"FIBTG\":7.5}}},{\"measures\":[{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"Apples\",\"foodId\":\"food_acsiefta0dtto6a7srju9axnqgmh\",\"categoryLabel\":\"food\",\"label\":\"Apples\",\"category\":\"Packaged foods\",\"brand\":\"Produce Marketing Association\",\"nutrients\":{\"ENERC_KCAL\":51.94805194805195,\"CHOCDF\":14.29220794083236,\"FIBTG\":3.1818182437450853}}},{\"measures\":[{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"Apples\",\"foodId\":\"food_alf4rnzbbw62pla3tdbosau0jh5s\",\"categoryLabel\":\"food\",\"label\":\"Apple\",\"category\":\"Packaged foods\",\"brand\":\"Honeybear Brands\",\"nutrients\":{\"ENERC_KCAL\":51.94805194805195,\"CHOCDF\":14.29220794083236,\"FIBTG\":3.1818182437450853}}},{\"measures\":[{\"label\":\"Whole\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_unit\"},{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"APPLES\",\"foodId\":\"food_alcw4z0brtm2ika8xgtzkbi1nqww\",\"categoryLabel\":\"food\",\"label\":\"APPLES\",\"category\":\"Packaged foods\",\"brand\":\"Heeren Bros Inc\",\"nutrients\":{\"PROCNT\":0.4099999964237213,\"ENERC_KCAL\":54,\"CHOCDF\":14.050000190734863,\"FIBTG\":2.0999999046325684}}},{\"measures\":[{\"label\":\"Whole\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_unit\"},{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"APPLE.\",\"foodId\":\"food_al8fpouakjzo4hanozgo4ayjilw5\",\"categoryLabel\":\"food\",\"label\":\"APPLES\",\"category\":\"Packaged foods\",\"brand\":\"Chelan Fresh Marketing\",\"nutrients\":{\"ENERC_KCAL\":52,\"CHOCDF\":14.289999961853027,\"FIBTG\":3.200000047683716}}},{\"measures\":[{\"label\":\"Whole\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_unit\"},{\"label\":\"Gram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_gram\"},{\"label\":\"Ounce\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_ounce\"},{\"label\":\"Pound\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_pound\"},{\"label\":\"Kilogram\",\"uri\":\"http://www.edamam.com/ontologies/edamam.owl#Measure_kilogram\"}],\"food\":{\"foodContentsLabel\":\"MICHIGAN APPLES\",\"foodId\":\"food_a17zvbibzshv16ayxky5zax6dddx\",\"categoryLabel\":\"food\",\"label\":\"APPLES\",\"category\":\"Packaged foods\",\"brand\":\"Produce Marketing Association\",\"nutrients\":{\"PROCNT\":0.4099999964237213,\"ENERC_KCAL\":54,\"CHOCDF\":14.050000190734863,\"FIBTG\":2.0999999046325684}}}],\"parsed\":[{\"food\":{\"foodId\":\"food_a1gb9ubb72c7snbuxr3weagwv0dd\",\"categoryLabel\":\"food\",\"label\":\"apple\",\"category\":\"Generic foods\",\"nutrients\":{\"PROCNT\":0.26,\"ENERC_KCAL\":52,\"FAT\":0.17,\"CHOCDF\":13.81,\"FIBTG\":2.4}}}],\"text\":\"apple\"}";
        final JSONObject jsonObject = new JSONObject(exampleResponse);
        final EdamamJSONParser edamamJSONParser = new EdamamJSONParser();

        //Act
        final List<Food> foods = edamamJSONParser.parseFoodResponse(jsonObject);

        //Assert
        assertEquals(20, foods.size());
    }
}