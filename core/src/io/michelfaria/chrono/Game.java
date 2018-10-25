package io.michelfaria.chrono;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

import io.michelfaria.chrono.consts.Assets;
import io.michelfaria.chrono.controller.ControllerEventEmitter;
import io.michelfaria.chrono.events.EventDispatcher;
import io.michelfaria.chrono.hud.MenuBoxes;
import io.michelfaria.chrono.screen.WalkScreen;

public class Game extends com.badlogic.gdx.Game {

    private AssetManager assetManager;
    private TextureAtlas atlas;
    private Batch batch;

    @Override
    public void create() {
        this.batch = new SpriteBatch();
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        EventDispatcher eventDispatcher = new EventDispatcher();
        ControllerEventEmitter controllerEventEmitter = new ControllerEventEmitter(eventDispatcher);

        // Load assets
        this.assetManager = new AssetManager();
        this.assetManager.load(Assets.CHRONO_ATLAS);
        this.assetManager.load(Assets.FONT);

        // Wait until done loading assets
        this.assetManager.finishLoading();

        // Done loading assets

        this.atlas = this.assetManager.get(Assets.CHRONO_ATLAS);
        MenuBoxes menuBoxes = new MenuBoxes(this.atlas);

        WalkScreen screen = new WalkScreen(this.batch, menuBoxes, this.assetManager, tmxMapLoader, this.atlas,
                eventDispatcher, controllerEventEmitter);
        eventDispatcher.addEventListener(screen);
        setScreen(screen);
    }

    @Override
    public void dispose() {
        assetManager.dispose();
        atlas.dispose();
        batch.dispose();
    }

}
