package ua.shvidkoy.webproject.controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import ua.shvidkoy.webproject.model.entity.CurrencyRate;

@WebServlet("/CurrencyPBRate")
public class CurrencyPBRate extends HttpServlet {
	private final static Logger LOGGER = Logger.getLogger(CurrencyPBRate.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String jsonCurrency = parseJson("https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5");

		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonCurrency);
		out.flush();
	}

	public static String parseJson(String url) throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();

		gsonBuilder.setPrettyPrinting();

		Gson gson = gsonBuilder.create();

		JsonReader jsonReader = new JsonReader(new InputStreamReader(new URL(url).openStream()));

		CurrencyRate[] currencyRates = gson.fromJson(jsonReader, CurrencyRate[].class);

		for (CurrencyRate currencyRate : currencyRates) {
			LOGGER.info(currencyRate);
		}

		String gsonString = gson.toJson(currencyRates);
		LOGGER.info(gsonString);

		return gsonString;
	}
}