/*
 * Generated by Robotoworks Mechanoid
 */
package com.robotoworks.example.movies.ops;

import com.robotoworks.mechanoid.Mechanoid;
import com.robotoworks.mechanoid.ops.Operation;
import com.robotoworks.mechanoid.ops.OperationResult;
import com.robotoworks.mechanoid.ops.OperationServiceBridge;
import com.robotoworks.mechanoid.ops.OperationConfiguration;
import android.content.Intent;
import android.os.Bundle;

public abstract class AbstractAddMovieOperation extends Operation {
	public static final String ACTION_ADD_MOVIE = "com.robotoworks.example.movies.ops.MoviesService.actions.ADD_MOVIE";

	public static final String EXTRA_TITLE = "com.robotoworks.example.movies.ops.MoviesService.extras.TITLE";
	public static final String EXTRA_DESCRIPTION = "com.robotoworks.example.movies.ops.MoviesService.extras.DESCRIPTION";
	public static final String EXTRA_YEAR = "com.robotoworks.example.movies.ops.MoviesService.extras.YEAR";

	static class Args {
		public String title;
		public String description;
		public int year;
	}
	
	static class Configuration extends OperationConfiguration {
		@Override 
		public Operation createOperation() {
			return new AddMovieOperation();
		}
		
		@Override
		public Intent findMatchOnConstraint(OperationServiceBridge bridge, Intent intent) {
			Intent existingRequest = bridge.findPendingRequestByActionWithExtras(AbstractAddMovieOperation.ACTION_ADD_MOVIE, intent.getExtras());
			
			return existingRequest;
			
		}
	}
	
	public static final Intent newIntent(String title, String description, int year) {
		Intent intent = new Intent(ACTION_ADD_MOVIE);
		intent.setClass(Mechanoid.getApplicationContext(), MoviesService.class);
		
		Bundle extras = new Bundle();
		extras.putString(EXTRA_TITLE, title);
		extras.putString(EXTRA_DESCRIPTION, description);
		extras.putInt(EXTRA_YEAR, year);
		
		intent.putExtras(extras);
		
		return intent;
		
	}

	@Override
	public OperationResult execute() {
		Args args = new Args();
		Bundle extras = getIntent().getExtras();
		args.title = extras.getString(EXTRA_TITLE);
		args.description = extras.getString(EXTRA_DESCRIPTION);
		args.year = extras.getInt(EXTRA_YEAR);
		
		return onExecute(args);
	}
			
	protected abstract OperationResult onExecute(Args args);
}
