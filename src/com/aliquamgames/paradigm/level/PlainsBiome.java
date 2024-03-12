package com.aliquamgames.paradigm.level;

import java.util.Random;

import com.aliquamgames.paradigm.Core;
import com.aliquamgames.paradigm.playing.Tile;
import com.aliquamgames.paradigm.weather.LightningStormWeather;
import com.aliquamgames.paradigm.weather.RainWeather;
import com.aliquamgames.paradigm.weather.Weather;

public class PlainsBiome extends Biome {

	public PlainsBiome() {
		weather = new Weather[2];
		weather[0] = new RainWeather();
		weather[1] = new LightningStormWeather();
	}

	@SuppressWarnings("static-access")
	@Override
	public void generate(int startX, int endX, int height) {
		// for (int x = startX; x < endX; x++) {
		// Core.level.block[x][20].id = Tile.dirtWithGrass;
		// }
		for (int x = startX; x < endX; x++) {
			for (int y = 0; y < Core.level.worldH; y++) {
				if (y > Core.level.worldH >> 4) {
					try {
						if (new Random().nextInt(30) < 5) {
							Core.level.block[x][y].id = Tile.dirt;
						}
					} catch (Exception e) {

					}
					try {
						if (new Random().nextInt(200) > 10) {
							if (Core.level.block[x][y - 1].id == Tile.dirt) {
								Core.level.block[x][y].id = Tile.dirt;
							}
						}
					} catch (Exception e) {
					}
					try {
						if (new Random().nextInt(200) < 87) {
							if (Core.level.block[x][y - 1].id == Tile.dirt) {
								Core.level.block[x][y + 1].id = Tile.dirt;
							}
						}
					} catch (Exception e) {

					}
					try {
						if (new Random().nextInt(200) < 160) {
							if (Core.level.block[x - 1][y - 1].id == Tile.dirt) {
								Core.level.block[x][y].id = Tile.dirt;
							}
						}
					} catch (Exception e) {

					}
					try {
						if (new Random().nextInt(200) < 35) {
							if (Core.level.block[x - 1][y].id == Tile.dirt && Core.level.block[x + 1][y].id == Tile.dirt) {
								Core.level.block[x][y].id = Tile.dirt;
							}
						}
					} catch (Exception e) {

					}
					try {
						if (new Random().nextInt(100) < 50) {
							if (Core.level.block[x][y + 1].id == Tile.dirt) {
								Core.level.block[x][y].id = Tile.dirt;
							}
						}
					} catch (Exception e) {

					}
					try {
						if (new Random().nextInt(100) < 25) {
							if (Core.level.block[x][y].id == Tile.air && Core.level.block[x + 1][y].id == Tile.dirt && Core.level.block[x - 1][y].id == Tile.dirt && Core.level.block[x][y - 1].id == Tile.dirt && Core.level.block[x][y + 1].id == Tile.dirt) {
								Core.level.block[x][y].id = Tile.dirt;
							}
						}
					} catch (Exception e) {

					}
				}

				// if (y > Core.level.worldH / 4) {
				// try {
				// if (new Random().nextInt(30) < 5) {
				// Core.level.block[x][y].id = Tile.stone;
				// }
				// } catch (Exception e) {
				//
				// }
				// try {
				// if (new Random().nextInt(200) > 10) {
				// if (Core.level.block[x][y - 1].id == Tile.stone) {
				// Core.level.block[x][y].id = Tile.stone;
				// }
				// }
				// } catch (Exception e) {
				// }
				// try {
				// if (new Random().nextInt(200) < 87) {
				// if (Core.level.block[x][y - 1].id == Tile.stone) {
				// Core.level.block[x][y + 1].id = Tile.stone;
				// }
				// }
				// } catch (Exception e) {
				//
				// }
				// try {
				// if (new Random().nextInt(200) < 160) {
				// if (Core.level.block[x - 1][y - 1].id == Tile.stone) {
				// Core.level.block[x][y].id = Tile.stone;
				// }
				// }
				// } catch (Exception e) {
				//
				// }
				// try {
				// if (new Random().nextInt(200) < 35) {
				// if (Core.level.block[x - 1][y].id == Tile.stone && Core.level.block[x + 1][y].id == Tile.stone) {
				// Core.level.block[x][y].id = Tile.stone;
				// }
				// }
				// } catch (Exception e) {
				//
				// }
				// try {
				// if (new Random().nextInt(100) < 50) {
				// if (Core.level.block[x][y + 1].id == Tile.stone) {
				// Core.level.block[x][y].id = Tile.stone;
				// }
				// }
				// } catch (Exception e) {
				//
				// }
				// try {
				// if (new Random().nextInt(100) < 25) {
				// if (Core.level.block[x][y].id == Tile.air && Core.level.block[x + 1][y].id == Tile.stone && Core.level.block[x - 1][y].id == Tile.stone && Core.level.block[x][y - 1].id == Tile.stone && Core.level.block[x][y + 1].id == Tile.stone)
				// {
				// Core.level.block[x][y].id = Tile.stone;
				// }
				// }
				// } catch (Exception e) {
				//
				// }
				// // TODO
				// // try {
				// // if(new Random().nextInt(10) < 1) {
				// // if(Core.level.block[x][y].id == Tile.dirt && Core.level.block[x+1][y].id == Tile.air && Core.level.block[x-1][y].id == Tile.air && Core.level.block[x][y-1].id == Tile.air && Core.level.block[x][y+1].id == Tile.air ||
				// // Core.level.block[x][y].id == Tile.dirtWithGrass && Core.level.block[x+1][y].id ==
				// // Tile.air && Core.level.block[x-1][y].id == Tile.air && Core.level.block[x][y-1].id == Tile.air && Core.level.block[x][y+1].id == Tile.air) {
				// // Core.level.block[x][y].id = Tile.air;
				// // }
				// // }
				// // }catch(Exception e) {
				// //
				// // }
				// }
			}
		}
		// plonk bedrock down
		// for (int x = startX; x < endX; x++) {
		// for (int y = 0; y < Core.level.worldH; y++) {
		// if (y == Core.level.worldH - 1 && x < Core.level.worldW - 1) {
		// Core.level.block[x][y].id = Tile.bedrock;
		// }
		// }
		// }

		// placing out the grass on top of the dirt
		for (int x = startX; x < endX; x++) {
			for (int y = 0; y < Core.level.worldH; y++) {
				if (Core.level.block[x][y].id == Tile.dirt && Core.level.block[x][y - 1].id == Tile.air) {
					Core.level.block[x][y].id = Tile.dirtWithGrass;
				}
			}
		}
		// TODO FIX
		// Core.level.getCol(0x00C418);
	}

}
