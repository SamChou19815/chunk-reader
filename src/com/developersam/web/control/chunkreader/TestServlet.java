package com.developersam.web.control.chunkreader;

import com.developersam.web.model.chunkreader.processor.MainProcessor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TestServlet", value="/chunkreader/test")
public class TestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MainProcessor processor = new MainProcessor("Hurricaine Gilbert Swept towrd the Dominican Republic Sunday, and the Civil Defense alerted its heavily populated south coast to prepare for high winds, heavy rains, and high seas.\n" +
                "The storm was approaching from the southeast with sustained winds of 75 mph gusting to 92 mph.\n" +
                "\"There is no need for alarm,\" Civil Defense Director Eugenio Cabral said in a television alert shortly after midnight Saturday.\n" +
                "Cabral said residents of the province of Barahona should closely follow Gilbert’s movement.\n" +
                "An estimated 100,000 people live in the province, including 70,000 in the city of Barahona, about 125 miles west of Santo Domingo.\n" +
                "Tropical storm Gilbert formed in the eastern Carribean and strenghtened into a hurricaine Saturday night.\n" +
                "The National Hurricaine Center in Miami reported its position at 2 a.m. Sunday at latitude 16.1 north, longitude 67.5 west, about 140 miles south of Ponce, Puerto Rico, and 200 miles southeast of Santo Domingo.\n" +
                "The National Weather Service in San Juan, Puerto Rico, said Gilbert was moving westard at 15 mph with a \"broad area of cloudiness and heavy weather\" rotating around the center of the storm.\n" +
                "The weather service issued a flash flood watch for Puerto Rico and the Virgin Islands until at least 6 p.m. Sunday.\n" +
                "Strong winds associated with the Gilbert brought coastal flooding, strong southeast winds, and up to 12 feet to Puerto Rico’s south coast.\n" +
                "There were no reports on casualties.\n" +
                "San Juan, on the north coast, had heavy rains and gusts Saturday, but they subsided during the night.\n" +
                "On Saturday, Hurricane Florence was downgraded to a tropical storm, and its remnants pushed inland from the U.S. Gulf Coast.\n" +
                "Residents returned home, happy to find little damage from 90 mph winds and sheets of rain.\n" +
                "Florence, the sixth named storm of the 1988 Atlantic storm season, was the second hurricane.\n" +
                "The first, Debby, reached minimal hurricane strength briefly before hitting the Mexican coast last month.");
        processor.process();
    }

}