package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;


import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import core.Equipment;
import core.GameEnvironment;
import core.Goods;
import core.Island;
import core.Route;
import core.Ship;
import core.Store;
import gui.Gui;
import seng201_project.GameEnviromentUi;

class GameEnvironmentTest {
	
	private static GameEnvironment game;

	@BeforeEach
	void setUp() throws Exception {
		final ArrayList<Ship> ships = new ArrayList<Ship>(4);
		
		ships.add(new Ship("Ship1", 5, 40, 100, 1200));
		ships.add(new Ship("Ship2", 7, 30, 100, 2400));
		ships.add(new Ship("Ship3", 5, 20, 100, 3600));
		ships.add(new Ship("Ship4",  10, 10, 100, 4800));
		
		
		final Goods rice = new Goods("Rice", 1.0);
		final Goods wheat = new Goods("Wheat", 1.2);
		final Goods sugar = new Goods("Sugar", 1.0);
		final Goods meat = new Goods("Meat", 1.5);
		final Goods milk = new Goods("Milk", 0.7);
		final Goods eggs = new Goods("Eggs", 0.6);
		final Goods guitar = new Goods("Guitar", 0.75);
		final Goods piano = new Goods("Piano", 2.9);
		final Goods flutes = new Goods("Flutes", 0.3);
		final Goods violin = new Goods("Violin", 0.2);
		final Goods dress = new Goods("Dress", 1.0);
		final Goods coats = new Goods("Coats", 1.5);
		final Goods pants = new Goods("Pants", 1.2);
		final Goods boots = new Goods("Boots", 1.5);
		final Goods shelf = new Goods("Shelf", 2.2);
		final Goods drawers = new Goods("Drawers", 2.0);
		final Goods wardrobe = new Goods("Wardrobe", 1.8);
		final Goods necklace = new Goods("Necklaces", 0.3);
		final Goods bracelets = new Goods("Bracelets", 0.2);
		final Goods earing = new Goods("Earings", 0.25);
		
		final Equipment cannons = new Equipment("Cannons",0.1, 50);
		final Equipment x_bows = new Equipment("X-bow",0.1, 50);
		final Equipment shield = new Equipment("Shield", 0.1, 50);
		final Equipment flamethrower = new Equipment("Falmethrower", 0.1, 50);
		final Equipment catapulte = new Equipment("Big catapulte", 0.1, 50);
		
		
		final HashMap<Goods, Integer> sellGoods1 = new HashMap<Goods, Integer>();
		
		sellGoods1.put(rice, 5);
		sellGoods1.put(wheat, 7);
		sellGoods1.put(sugar, 4);
		sellGoods1.put(meat, 10);
		sellGoods1.put(milk, 7);
		sellGoods1.put(eggs, 6);
		
		final HashMap<Goods, Integer> interestedGoods1 = new HashMap<Goods, Integer>();
		
		interestedGoods1.put(guitar, 45);
		interestedGoods1.put(piano, 54);
		interestedGoods1.put(flutes, 50);
		interestedGoods1.put(violin, 46);
		interestedGoods1.put(necklace, 42);
		interestedGoods1.put(bracelets, 51);
		interestedGoods1.put(earing, 40);
		

		Store storeSumatra = new Store(sellGoods1,interestedGoods1, cannons);
		
		
		
		//for island2
		final HashMap<Goods, Integer> sellGoods2 = new HashMap<Goods, Integer>();
		
		sellGoods2.put(shelf, 20);
		sellGoods2.put(wardrobe, 17);
		sellGoods2.put(drawers, 18);
		
		final HashMap<Goods, Integer> interestedGoods2 = new HashMap<Goods, Integer>();
		
		interestedGoods2.put(dress, 28);
		interestedGoods2.put(coats, 32);
		interestedGoods2.put(pants, 26);
		interestedGoods2.put(boots, 27);
		interestedGoods2.put(guitar, 47);
		interestedGoods2.put(piano, 53);
		interestedGoods2.put(flutes, 48);
		interestedGoods2.put(violin, 47);
		
		Store storeKalimantan = new Store(sellGoods2, interestedGoods2, x_bows);
		
		//for island3
		final HashMap<Goods, Integer> sellGoods3 = new HashMap<Goods, Integer>();
				
		sellGoods3.put(necklace, 30);
		sellGoods3.put(bracelets, 35);
		sellGoods3.put(earing, 27);
				
		final HashMap<Goods, Integer> interestedGoods3 = new HashMap<Goods, Integer>();
				
		interestedGoods3.put(rice, 17);
		interestedGoods3.put(wheat, 20);
		interestedGoods3.put(sugar, 18);
		interestedGoods3.put(meat, 21);
		interestedGoods3.put(milk, 18);
		interestedGoods3.put(eggs, 19);
		interestedGoods3.put(shelf, 33);
		interestedGoods3.put(wardrobe, 30);
		interestedGoods3.put(drawers, 31);
		interestedGoods3.put(guitar, 45);
		interestedGoods3.put(piano, 55);
		interestedGoods3.put(flutes, 49);
		interestedGoods3.put(violin, 47);
		
				
		Store storeSulawesi = new Store(sellGoods3, interestedGoods3, catapulte);
		
		//for island4
		final HashMap<Goods, Integer> sellGoods4 = new HashMap<Goods, Integer>();
				
		sellGoods4.put(dress, 15);
		sellGoods4.put(coats, 19);
		sellGoods4.put(pants, 13);
		sellGoods4.put(boots, 14);
				
		final HashMap<Goods, Integer> interestedGoods4 = new HashMap<Goods, Integer>();
				
		interestedGoods4.put(necklace, 45);
		interestedGoods4.put(bracelets, 46);
		interestedGoods4.put(earing, 40);
		interestedGoods4.put(rice, 16);
		interestedGoods4.put(wheat, 20);
		interestedGoods4.put(sugar, 15);
		interestedGoods4.put(meat, 25);
		interestedGoods4.put(milk, 18);
		interestedGoods4.put(eggs, 17);
		interestedGoods4.put(shelf, 35);
		interestedGoods4.put(wardrobe, 28);
		interestedGoods4.put(drawers, 30);
				
		Store storePapua = new Store(sellGoods4, interestedGoods4, flamethrower);
						
		//for island5
		final HashMap<Goods, Integer> sellGoods5 = new HashMap<Goods, Integer>();
				
		sellGoods5.put(guitar, 32);
		sellGoods5.put(piano, 40);
		sellGoods5.put(flutes, 35);
		sellGoods5.put(violin, 33);
				
		final HashMap<Goods, Integer> interestedGoods5 = new HashMap<Goods, Integer>();
				
		interestedGoods5.put(rice, 17);
		interestedGoods5.put(wheat, 22);
		interestedGoods5.put(sugar, 16);
		interestedGoods5.put(meat, 22);
		interestedGoods5.put(milk, 20);
		interestedGoods5.put(eggs, 18);
		interestedGoods5.put(dress, 29);
		interestedGoods5.put(coats, 32);
		interestedGoods5.put(pants, 25);
		interestedGoods5.put(boots, 30);
		interestedGoods5.put(necklace, 44);
		interestedGoods5.put(bracelets, 49);
		interestedGoods5.put(earing, 38);
		
				
		Store storeJawa = new Store(sellGoods5, interestedGoods5, shield);
		
		
		Route routeSumatraKalimantan = new Route(5200, "Sumatra", "Kalimantan", 70);
		Route routeSumatraSulawesi = new Route(6500, "Sumatra", "Sulawesi", 40);
		Route routeSumatraPapua = new Route(7000, "Sumatra", "Papua", 80);
		Route routeSumatraJawa = new Route(5100, "Sumatra", "Jawa", 20);
		Route routeKalimantanSulawesi = new Route(5000, "Kalimantan", "Sulawesi", 50);
		Route routeKalimantanJawa = new Route(5100, "Jawa", "Kalimantan",30);
		Route routeKalimantanPapua = new Route(6000, "Papua", "Kalimantan", 60);
		Route routeSulawesiPapua = new Route(5000, "Sulawesi", "Papua", 40);
		Route routeSulawesiJawa = new Route(5500, "Sulawesi", "Jawa", 50);
		Route routePapuaJawa = new Route(6900, "Jawa", "Papua", 30);
								
		final ArrayList<Route> listRouteIslandSumatra = new ArrayList<Route>();
		listRouteIslandSumatra.add(routeSumatraJawa);
		listRouteIslandSumatra.add(routeSumatraKalimantan);
		listRouteIslandSumatra.add(routeSumatraSulawesi);
		listRouteIslandSumatra.add(routeSumatraPapua);
		
		final ArrayList<Route> listRouteIslandJawa = new ArrayList<Route>();
		listRouteIslandJawa.add(routeSumatraJawa);
		listRouteIslandJawa.add(routeKalimantanJawa);
		listRouteIslandJawa.add(routeSulawesiJawa);
		listRouteIslandJawa.add(routePapuaJawa);

		final ArrayList<Route> listRouteIslandKalimantan = new ArrayList<Route>();
		listRouteIslandKalimantan.add(routeSumatraKalimantan);
		listRouteIslandKalimantan.add(routeKalimantanJawa);
		listRouteIslandKalimantan.add(routeKalimantanSulawesi);
		listRouteIslandKalimantan.add(routeKalimantanPapua);
		
		final ArrayList<Route> listRouteIslandSulawesi = new ArrayList<Route>();
		listRouteIslandSulawesi.add(routeSumatraSulawesi);
		listRouteIslandSulawesi.add(routeSulawesiPapua);
		listRouteIslandSulawesi.add(routeSulawesiJawa);
		listRouteIslandSulawesi.add(routeKalimantanSulawesi);
		
		final ArrayList<Route> listRouteIslandPapua = new ArrayList<Route>();
		listRouteIslandPapua.add(routeSumatraPapua);
		listRouteIslandPapua.add(routeSulawesiPapua);
		listRouteIslandPapua.add(routePapuaJawa);
		listRouteIslandPapua.add(routeKalimantanPapua);
		
		
		Island islandJawa = new Island("Jawa", storeJawa, listRouteIslandJawa);
		Island islandSumatra = new Island("Sumatra", storeSumatra, listRouteIslandSumatra);
		Island islandKalimantan = new Island("Kalimantan", storeKalimantan, listRouteIslandKalimantan);
		Island islandSulawesi = new Island("Sulawesi", storeSulawesi, listRouteIslandSulawesi);
		Island islandPapua = new Island("Papua", storePapua, listRouteIslandPapua);
		
		final ArrayList<Island> listIslands = new ArrayList<Island>();
		listIslands.add(islandJawa);
		listIslands.add(islandSumatra);
		listIslands.add(islandKalimantan);
		listIslands.add(islandSulawesi);
		listIslands.add(islandPapua);
		
		
			
		GameEnviromentUi ui;

		ui = new Gui();
		game = new GameEnvironment(ui, ships, listIslands, 250);
		Ship ship1 = new Ship("Ship1", 5, 40, 100, 1200);
		game.setSelectedShip(ship1);
	}
	
	
	
	@Test 
	public void chooseOtherIslandTest() {
		
	}
	
	
	
	@Test 
	public void calculateSailingDaysTest() {
		Route routeSumatraKalimantan = new Route(5200, "Sumatra", "Kalimantan", 70);
		int output = game.calculateSailingDays(routeSumatraKalimantan);
		assertEquals(4, output);
	}
	
	@Test
	public void updateMoneyTest() {
		game.updateMoney(10);
		assertEquals(260, game.getMoney());
	}
	
	@Test
	public void getListOfRoutesTest() {
		Route routeSumatraKalimantan = new Route(5200, "Sumatra", "Kalimantan", 70);
		Route routeSumatraSulawesi = new Route(6500, "Sumatra", "Sulawesi", 40);
		Route routeSumatraPapua = new Route(7000, "Sumatra", "Papua", 80);
		Route routeSumatraJawa = new Route(5100, "Sumatra", "Jawa", 20);

		final ArrayList<Route> listRouteIslandSumatra = new ArrayList<Route>();
		listRouteIslandSumatra.add(routeSumatraJawa);
		listRouteIslandSumatra.add(routeSumatraKalimantan);
		listRouteIslandSumatra.add(routeSumatraSulawesi);
		listRouteIslandSumatra.add(routeSumatraPapua);
		
		final Goods rice = new Goods("Rice", 1.0);
		final Goods wheat = new Goods("Wheat", 1.2);
		final Goods sugar = new Goods("Sugar", 1.0);
		final Goods meat = new Goods("Meat", 1.5);
		final Goods milk = new Goods("Milk", 0.7);
		final Goods eggs = new Goods("Eggs", 0.6);
		final Goods guitar = new Goods("Guitar", 0.75);
		final Goods piano = new Goods("Piano", 2.9);
		final Goods flutes = new Goods("Flutes", 0.3);
		final Goods violin = new Goods("Violin", 0.2);
		final Goods necklace = new Goods("Necklaces", 0.3);
		final Goods bracelets = new Goods("Bracelets", 0.2);
		final Goods earing = new Goods("Earings", 0.25);
		
		final Equipment cannons = new Equipment("Cannons",0.1, 50);
		final HashMap<Goods, Integer> sellGoods1 = new HashMap<Goods, Integer>();
		
		sellGoods1.put(rice, 5);
		sellGoods1.put(wheat, 7);
		sellGoods1.put(sugar, 4);
		sellGoods1.put(meat, 10);
		sellGoods1.put(milk, 7);
		sellGoods1.put(eggs, 6);
		
		final HashMap<Goods, Integer> interestedGoods1 = new HashMap<Goods, Integer>();
		
		interestedGoods1.put(guitar, 45);
		interestedGoods1.put(piano, 54);
		interestedGoods1.put(flutes, 50);
		interestedGoods1.put(violin, 46);
		interestedGoods1.put(necklace, 42);
		interestedGoods1.put(bracelets, 51);
		interestedGoods1.put(earing, 40);
		

		Store storeSumatra = new Store(sellGoods1,interestedGoods1, cannons);
		Island islandSumatra = new Island("Sumatra", storeSumatra, listRouteIslandSumatra);
		game.setCurrentLocation(islandSumatra);
		assertEquals(listRouteIslandSumatra, game.getListOfRoutes());
		int output = storeSumatra.getSellGoods().size();
		assertEquals(6, output);
	}
	
	

	@Test
	public void checkConditionForBuyingTest() {
		Goods earing = new Goods("Earings", 0.25);
		assertFalse(game.checkConditionsForBuying(100, earing, 30));
		assertTrue(game.checkConditionsForBuying(1, earing, 30));
		assertFalse(game.checkConditionsForBuying(1, earing, 1000));// false in checkEnoughMoney
		assertFalse(game.checkConditionsForBuying(1000, earing, 0));// false in checkEnoughCapacity
	}
	
	@Test
	public void buyGoodsTest() {
		
		Goods earing = new Goods("Earings", 0.25);
		game.buyGoods(1, earing, 10);
		assertEquals(240, game.getMoney());
		assertEquals(1, game.getSelectedShip().getMyGoods().size());
		
		
	}
	
	@Test
	public void checkAvailablelityTest() {
		Goods earing = new Goods("Earings", 0.25);
		assertFalse(game.checkAvailability(earing));
		game.buyGoods(1, earing, 20);
		assertTrue(game.checkAvailability(earing));
	}
	
	@Test
	public void checkQuantityTest() {
		Goods earing = new Goods("Earings", 0.25);
		assertFalse(game.checkQuantity(1, earing));
		game.buyGoods(1, earing, 20);
		assertTrue(game.checkQuantity(1, earing));
	}
	
	@Test
	public void sellGoodsTest() {
		Goods earing = new Goods("Earings", 0.25);
		game.buyGoods(1, earing, 0);
		game.sellGoods(earing, 1, 10);
		assertEquals(260, game.getMoney());
		assertEquals(0, game.getSelectedShip().getMyGoods().size());
		assertEquals(1, game.getSelectedShip().getSoldItems().size());
	}
	
	@Test
	public void checkEquipmentTest() {
		assertFalse(game.checkEquipmentFull());
		final Equipment cannons = new Equipment("Cannons",0.1, 50);
		final Equipment x_bows = new Equipment("X-bow",0.1, 50);
		final Equipment shield = new Equipment("Shield", 0.1, 50);
		final Equipment flamethrower = new Equipment("Falmethrower", 0.1, 50);
		final Equipment catapulte = new Equipment("Big catapulte", 0.1, 50);
		
		game.getSelectedShip().addEquipment(catapulte);
		game.getSelectedShip().addEquipment(cannons);
		game.getSelectedShip().addEquipment(x_bows);
		game.getSelectedShip().addEquipment(shield);
		game.getSelectedShip().addEquipment(flamethrower);
		assertTrue(game.checkEquipmentFull());
	}
	
	@Test
	public void buyEquipmentTest() {
		Equipment catapulte = new Equipment("Big catapulte", 0.1, 50);
		game.buyEquipment(catapulte);
		assertEquals(200, game.getMoney());
		assertEquals(1, game.getSelectedShip().getEquipment().size());
		assertEquals(0.6, game.getSelectedShip().getProbability());
	}

	@Test
	public void checkMyShipsHealthTest() {
		assertTrue(game.checkMyShipHealth());
		game.getSelectedShip().setShipStatus(99);
		assertFalse(game.checkMyShipHealth());
	}
	
	@Test
	public void calculateRepairrmentCostTest() {
		assertEquals(0, game.calculateRepairmentCost());
		game.getSelectedShip().setShipStatus(50);
		assertEquals(50, game.calculateRepairmentCost());
	}
	
	@Test
	public void checkItemAvailability() {
		Route routeSumatraKalimantan = new Route(5200, "Sumatra", "Kalimantan", 70);
		Route routeSumatraSulawesi = new Route(6500, "Sumatra", "Sulawesi", 40);
		Route routeSumatraPapua = new Route(7000, "Sumatra", "Papua", 80);
		Route routeSumatraJawa = new Route(5100, "Sumatra", "Jawa", 20);

		final ArrayList<Route> listRouteIslandSumatra = new ArrayList<Route>();
		listRouteIslandSumatra.add(routeSumatraJawa);
		listRouteIslandSumatra.add(routeSumatraKalimantan);
		listRouteIslandSumatra.add(routeSumatraSulawesi);
		listRouteIslandSumatra.add(routeSumatraPapua);
		
		final Goods rice = new Goods("Rice", 1.0);
		final Goods wheat = new Goods("Wheat", 1.2);
		final Goods sugar = new Goods("Sugar", 1.0);
		final Goods meat = new Goods("Meat", 1.5);
		final Goods milk = new Goods("Milk", 0.7);
		final Goods eggs = new Goods("Eggs", 0.6);
		final Goods guitar = new Goods("Guitar", 0.75);
		final Goods piano = new Goods("Piano", 2.9);
		final Goods flutes = new Goods("Flutes", 0.3);
		final Goods violin = new Goods("Violin", 0.2);
		final Goods necklace = new Goods("Necklaces", 0.3);
		final Goods bracelets = new Goods("Bracelets", 0.2);
		final Goods earing = new Goods("Earings", 0.25);
		
		final Equipment cannons = new Equipment("Cannons",0.1, 50);
		final HashMap<Goods, Integer> sellGoods1 = new HashMap<Goods, Integer>();
		
		sellGoods1.put(rice, 5);
		sellGoods1.put(wheat, 7);
		sellGoods1.put(sugar, 4);
		sellGoods1.put(meat, 10);
		sellGoods1.put(milk, 7);
		sellGoods1.put(eggs, 6);
		
		final HashMap<Goods, Integer> interestedGoods1 = new HashMap<Goods, Integer>();
		
		interestedGoods1.put(guitar, 45);
		interestedGoods1.put(piano, 54);
		interestedGoods1.put(flutes, 50);
		interestedGoods1.put(violin, 46);
		interestedGoods1.put(necklace, 42);
		interestedGoods1.put(bracelets, 51);
		interestedGoods1.put(earing, 40);
		

		Store storeSumatra = new Store(sellGoods1,interestedGoods1, cannons);
		Island islandSumatra = new Island("Sumatra", storeSumatra, listRouteIslandSumatra);
		game.setCurrentLocation(islandSumatra);
		Goods shelf = new Goods("Shelf", 2.2);
		game.buyGoods(1, shelf, 20);
		assertFalse(game.checkItemAvailability());
		game.buyGoods(1, earing, 10);
		assertTrue(game.checkItemAvailability());
	}
	
	@Test
	public void getIncidentRoutesTest() {
		final Goods rice = new Goods("Rice", 1.0);
		final Goods wheat = new Goods("Wheat", 1.2);
		final Goods sugar = new Goods("Sugar", 1.0);
		final Goods meat = new Goods("Meat", 1.5);
		final Goods milk = new Goods("Milk", 0.7);
		final Goods eggs = new Goods("Eggs", 0.6);
		final Goods guitar = new Goods("Guitar", 0.75);
		final Goods piano = new Goods("Piano", 2.9);
		final Goods flutes = new Goods("Flutes", 0.3);
		final Goods violin = new Goods("Violin", 0.2);
		final Goods dress = new Goods("Dress", 1.0);
		final Goods coats = new Goods("Coats", 1.5);
		final Goods pants = new Goods("Pants", 1.2);
		final Goods boots = new Goods("Boots", 1.5);
		final Goods shelf = new Goods("Shelf", 2.2);
		final Goods drawers = new Goods("Drawers", 2.0);
		final Goods wardrobe = new Goods("Wardrobe", 1.8);
		final Goods necklace = new Goods("Necklaces", 0.3);
		final Goods bracelets = new Goods("Bracelets", 0.2);
		final Goods earing = new Goods("Earings", 0.25);
		
		final Equipment cannons = new Equipment("Cannons",0.1, 50);
		final Equipment x_bows = new Equipment("X-bow",0.1, 50);
		
		
		final HashMap<Goods, Integer> sellGoods1 = new HashMap<Goods, Integer>();
		
		sellGoods1.put(rice, 5);
		sellGoods1.put(wheat, 7);
		sellGoods1.put(sugar, 4);
		sellGoods1.put(meat, 10);
		sellGoods1.put(milk, 7);
		sellGoods1.put(eggs, 6);
		
		final HashMap<Goods, Integer> interestedGoods1 = new HashMap<Goods, Integer>();
		
		interestedGoods1.put(guitar, 45);
		interestedGoods1.put(piano, 54);
		interestedGoods1.put(flutes, 50);
		interestedGoods1.put(violin, 46);
		interestedGoods1.put(necklace, 42);
		interestedGoods1.put(bracelets, 51);
		interestedGoods1.put(earing, 40);
		

		Store storeSumatra = new Store(sellGoods1,interestedGoods1, cannons);
		
		
		
		//for island2
		final HashMap<Goods, Integer> sellGoods2 = new HashMap<Goods, Integer>();
		
		sellGoods2.put(shelf, 20);
		sellGoods2.put(wardrobe, 17);
		sellGoods2.put(drawers, 18);
		
		final HashMap<Goods, Integer> interestedGoods2 = new HashMap<Goods, Integer>();
		
		interestedGoods2.put(dress, 28);
		interestedGoods2.put(coats, 32);
		interestedGoods2.put(pants, 26);
		interestedGoods2.put(boots, 27);
		interestedGoods2.put(guitar, 47);
		interestedGoods2.put(piano, 53);
		interestedGoods2.put(flutes, 48);
		interestedGoods2.put(violin, 47);
		
		Store storeKalimantan = new Store(sellGoods2, interestedGoods2, x_bows);
		
		Route routeSumatraKalimantan = new Route(5200, "Sumatra", "Kalimantan", 70);
		Route routeSumatraSulawesi = new Route(6500, "Sumatra", "Sulawesi", 40);
		Route routeSumatraPapua = new Route(7000, "Sumatra", "Papua", 80);
		Route routeSumatraJawa = new Route(5100, "Sumatra", "Jawa", 20);
		Route routeKalimantanSulawesi = new Route(5000, "Kalimantan", "Sulawesi", 50);
		Route routeKalimantanJawa = new Route(5100, "Jawa", "Kalimantan",30);
		Route routeKalimantanPapua = new Route(6000, "Papua", "Kalimantan", 60);

		final ArrayList<Route> listRouteIslandSumatra = new ArrayList<Route>();
		listRouteIslandSumatra.add(routeSumatraJawa);
		listRouteIslandSumatra.add(routeSumatraKalimantan);
		listRouteIslandSumatra.add(routeSumatraSulawesi);
		listRouteIslandSumatra.add(routeSumatraPapua);
		
		final ArrayList<Route> listRouteIslandKalimantan = new ArrayList<Route>();
		listRouteIslandKalimantan.add(routeSumatraKalimantan);
		listRouteIslandKalimantan.add(routeKalimantanJawa);
		listRouteIslandKalimantan.add(routeKalimantanSulawesi);
		listRouteIslandKalimantan.add(routeKalimantanPapua);
		
		Island islandSumatra = new Island("Sumatra", storeSumatra, listRouteIslandSumatra);
		Island islandKalimantan = new Island("Kalimantan", storeKalimantan, listRouteIslandKalimantan);
		
		game.setCurrentChosenIsland(islandKalimantan);
		game.setCurrentLocation(islandSumatra);
		
		assertEquals(routeSumatraKalimantan, game.getIncidentRoutes());
		game.setCurrentChosenIsland(islandSumatra);
		game.setCurrentLocation(islandKalimantan);
		
		assertEquals(routeSumatraKalimantan, game.getIncidentRoutes());
	}

	@Test
	public void determineWinnningRPSGameTest() {
		game.getSelectedShip().setProbability(1.0);
		assertEquals(2, game.determineWinnningRPSGame());
	}
	
	@Test
	public void getWhatPiratesThrowRPSTest() {
		assertEquals("Rock",game.getWhatPiratesThrowRPS(1, "Rock"));
		assertEquals("Scissors",game.getWhatPiratesThrowRPS(0, "Paper"));
		assertEquals("Paper",game.getWhatPiratesThrowRPS(2, "Scissors"));
	}
	
	@Test
	public void removeAllGoodsTest() {
		Goods rice = new Goods("Rice", 1.0);
		Goods wheat = new Goods("Wheat", 1.2);
		game.buyGoods(1, wheat, 10);
		game.buyGoods(1, rice, 10);
		assertEquals(2, game.getSelectedShip().getMyGoods().size());
		game.removeAllGoods();
		assertEquals(0, game.getSelectedShip().getMyGoods().size());
	}
	

	
	@Test
	public void checkAccessibleIslandTest() {
		Route routeSumatraKalimantan = new Route(5200, "Sumatra", "Kalimantan", 70);
		Route routeSumatraSulawesi = new Route(6500, "Sumatra", "Sulawesi", 40);
		Route routeSumatraPapua = new Route(7000, "Sumatra", "Papua", 80);
		Route routeSumatraJawa = new Route(5100, "Sumatra", "Jawa", 20);

		final ArrayList<Route> listRouteIslandSumatra = new ArrayList<Route>();
		listRouteIslandSumatra.add(routeSumatraJawa);
		listRouteIslandSumatra.add(routeSumatraKalimantan);
		listRouteIslandSumatra.add(routeSumatraSulawesi);
		listRouteIslandSumatra.add(routeSumatraPapua);
		
		final Goods rice = new Goods("Rice", 1.0);
		final Goods wheat = new Goods("Wheat", 1.2);
		final Goods sugar = new Goods("Sugar", 1.0);
		final Goods meat = new Goods("Meat", 1.5);
		final Goods milk = new Goods("Milk", 0.7);
		final Goods eggs = new Goods("Eggs", 0.6);
		final Goods guitar = new Goods("Guitar", 0.75);
		final Goods piano = new Goods("Piano", 2.9);
		final Goods flutes = new Goods("Flutes", 0.3);
		final Goods violin = new Goods("Violin", 0.2);
		final Goods necklace = new Goods("Necklaces", 0.3);
		final Goods bracelets = new Goods("Bracelets", 0.2);
		final Goods earing = new Goods("Earings", 0.25);
		
		final Equipment cannons = new Equipment("Cannons",0.1, 50);
		final HashMap<Goods, Integer> sellGoods1 = new HashMap<Goods, Integer>();
		
		sellGoods1.put(rice, 5);
		sellGoods1.put(wheat, 7);
		sellGoods1.put(sugar, 4);
		sellGoods1.put(meat, 10);
		sellGoods1.put(milk, 7);
		sellGoods1.put(eggs, 6);
		
		final HashMap<Goods, Integer> interestedGoods1 = new HashMap<Goods, Integer>();
		
		interestedGoods1.put(guitar, 45);
		interestedGoods1.put(piano, 54);
		interestedGoods1.put(flutes, 50);
		interestedGoods1.put(violin, 46);
		interestedGoods1.put(necklace, 42);
		interestedGoods1.put(bracelets, 51);
		interestedGoods1.put(earing, 40);
		

		Store storeSumatra = new Store(sellGoods1,interestedGoods1, cannons);
		Island islandSumatra = new Island("Sumatra", storeSumatra, listRouteIslandSumatra);
		game.setCurrentLocation(islandSumatra);
		game.setRemainingDays(50);
		assertTrue(game.checkAccessibleIsland(islandSumatra));
		game.setRemainingDays(1);
		assertFalse(game.checkAccessibleIsland(islandSumatra));
		storeSumatra.getEquipment();
		assertEquals(cannons, storeSumatra.getEquipment());
		cannons.getName();
		assertEquals("Cannons", cannons.getName());
	}
	
	@Test
	public void isIslandReachableTest() {
		Route routeSumatraJawa = new Route(5100, "Sumatra", "Jawa", 20);
		game.setRemainingDays(50);
		assertTrue(game.isIslandReachable(routeSumatraJawa));
		game.setRemainingDays(0);
		assertFalse(game.isIslandReachable(routeSumatraJawa));
		routeSumatraJawa.getProbability();
		assertEquals(20, routeSumatraJawa.getProbability());
	} 
	
	@Test
	public void countProfitTest() {
		assertEquals(0,game.countProfit());
	}
	
	@Test
	public void countFnalScoreTest() {
		game.setTravellingDays(1);
		game.setMoney(350);
		assertEquals(101,game.countFinalScore());
	}
	

}
