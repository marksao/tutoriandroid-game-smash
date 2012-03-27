package com.tutoriandroid.games.smash;

import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.gdacarv.engine.androidgame.GameView;

public class MainGameView extends GameView {
	
	protected int level = 0, score = 0;
	
	protected ArrayList<Pink> pinks;
	protected ArrayList<Gold> golds;
	protected Background background;

	public MainGameView(Context context) {
		super(context);
		
	}

	@Override
	public void TouchEvents(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	protected void onLoad() {
		pinks = new ArrayList<Pink>();
		golds = new ArrayList<Gold>();
		Random random = new Random();
		Resources res = getResources();
		Bitmap bitmapPink = BitmapFactory.decodeResource(res, R.drawable.pink);
		Bitmap bitmapGold = BitmapFactory.decodeResource(res, R.drawable.gold_head);
		int limitPinkX = getWidth()-bitmapPink.getWidth()/6,
			limitPinkY = getHeight()-bitmapPink.getHeight()/5,
			limitGoldX = getWidth()-bitmapGold.getWidth()/8,
			limitGoldY = getHeight()-bitmapGold.getHeight();
		for(int i = 0; i < 10; i++)
			pinks.add(new Pink(random.nextInt(limitPinkX), random.nextInt(limitPinkY), random, bitmapPink, 5, 6));
		for(int i = 0; i < 10; i++)
			golds.add(new Gold(random.nextInt(limitGoldX), random.nextInt(limitGoldY), random, bitmapGold, 1, 8));
		background = new Background(random, getWidth(), getHeight(), BitmapFactory.decodeResource(res, R.drawable.background));
		mSprites.add(background);
		mSprites.addAll(pinks);
		mSprites.addAll(golds);
	}
	
	@Override
	public void update() {
		super.update();
		for(Pink pink : pinks)
			if(pink.x < 0)
				pink.changeDirection((byte) (4 - pink.direction % 4));
			else if(pink.x > getWidth()-pink.width)
				pink.changeDirection((byte) (8 - pink.direction));
			else if(pink.y < 0)
				pink.changeDirection((byte) ((12 - pink.direction) % 8));
			else if(pink.y > getHeight()-pink.height)
				pink.changeDirection((byte) (5 - (pink.direction+1) % 8));
		for(Gold gold : golds)
			if(gold.x < 0 || gold.x > getWidth()-gold.width)
				gold.speedX *= -1;
			else if(gold.y < 0 || gold.y > getHeight()-gold.height)
				gold.speedY *= -1;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}

}
