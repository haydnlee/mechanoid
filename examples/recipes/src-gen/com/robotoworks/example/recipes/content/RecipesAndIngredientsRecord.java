/*
 * Generated by Robotoworks Mechanoid
 */
package com.robotoworks.example.recipes.content;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.robotoworks.example.recipes.content.RecipesDBContract.RecipesAndIngredients;
import com.robotoworks.example.recipes.content.RecipesDBContract.RecipesAndIngredients.Builder;
import com.robotoworks.mechanoid.util.Closeables;
import com.robotoworks.mechanoid.sqlite.ActiveRecord;
import com.robotoworks.mechanoid.sqlite.ActiveRecordFactory;
import com.robotoworks.mechanoid.Mechanoid;
import com.robotoworks.mechanoid.content.AbstractValuesBuilder;

public class RecipesAndIngredientsRecord extends ActiveRecord implements Parcelable {

	private static ActiveRecordFactory<RecipesAndIngredientsRecord> sFactory = new ActiveRecordFactory<RecipesAndIngredientsRecord>() {
		@Override
		public RecipesAndIngredientsRecord create(Cursor c) {
			return fromCursor(c);
		}
		
		@Override
		public String[] getProjection() {
			return PROJECTION;
		}
	};
	
	public static ActiveRecordFactory<RecipesAndIngredientsRecord> getFactory() {
		return sFactory;
	}

    public static final Parcelable.Creator<RecipesAndIngredientsRecord> CREATOR 
    	= new Parcelable.Creator<RecipesAndIngredientsRecord>() {
        public RecipesAndIngredientsRecord createFromParcel(Parcel in) {
            return new RecipesAndIngredientsRecord(in);
        }

        public RecipesAndIngredientsRecord[] newArray(int size) {
            return new RecipesAndIngredientsRecord[size];
        }
    };
    
    public static String[] PROJECTION = {
    	RecipesAndIngredients._ID,
    	RecipesAndIngredients.ROW_TYPE,
    	RecipesAndIngredients.TITLE,
    	RecipesAndIngredients.DESCRIPTION,
    	RecipesAndIngredients.INGREDIENT_QUANTITY,
    	RecipesAndIngredients.SORT_KEY
    };
    
    public interface Indices {
    	int _ID = 0;
    	int ROW_TYPE = 1;
    	int TITLE = 2;
    	int DESCRIPTION = 3;
    	int INGREDIENT_QUANTITY = 4;
    	int SORT_KEY = 5;
    }
    
    private String mRowType;
    private boolean mRowTypeDirty;
    private String mTitle;
    private boolean mTitleDirty;
    private String mDescription;
    private boolean mDescriptionDirty;
    private String mIngredientQuantity;
    private boolean mIngredientQuantityDirty;
    private String mSortKey;
    private boolean mSortKeyDirty;
    
    @Override
    protected String[] _getProjection() {
    	return PROJECTION;
    }
    
    public void setRowType(String rowType) {
    	mRowType = rowType;
    	mRowTypeDirty = true;
    }
    
    public String getRowType() {
    	return mRowType;
    }
    public void setTitle(String title) {
    	mTitle = title;
    	mTitleDirty = true;
    }
    
    public String getTitle() {
    	return mTitle;
    }
    public void setDescription(String description) {
    	mDescription = description;
    	mDescriptionDirty = true;
    }
    
    public String getDescription() {
    	return mDescription;
    }
    public void setIngredientQuantity(String ingredientQuantity) {
    	mIngredientQuantity = ingredientQuantity;
    	mIngredientQuantityDirty = true;
    }
    
    public String getIngredientQuantity() {
    	return mIngredientQuantity;
    }
    public void setSortKey(String sortKey) {
    	mSortKey = sortKey;
    	mSortKeyDirty = true;
    }
    
    public String getSortKey() {
    	return mSortKey;
    }
    
    public RecipesAndIngredientsRecord() {
    	super(RecipesAndIngredients.CONTENT_URI);
	}
	
	private RecipesAndIngredientsRecord(Parcel in) {
    	super(RecipesAndIngredients.CONTENT_URI);
    	
		setId(in.readLong());
		
		mRowType = in.readString();
		mTitle = in.readString();
		mDescription = in.readString();
		mIngredientQuantity = in.readString();
		mSortKey = in.readString();
		
		boolean[] dirtyFlags = new boolean[6];
		in.readBooleanArray(dirtyFlags);
		mRowTypeDirty = dirtyFlags[0];
		mTitleDirty = dirtyFlags[1];
		mDescriptionDirty = dirtyFlags[2];
		mIngredientQuantityDirty = dirtyFlags[3];
		mSortKeyDirty = dirtyFlags[4];
	}
	
	@Override
	public int describeContents() {
	    return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeLong(getId());
		dest.writeString(mRowType);
		dest.writeString(mTitle);
		dest.writeString(mDescription);
		dest.writeString(mIngredientQuantity);
		dest.writeString(mSortKey);
		dest.writeBooleanArray(new boolean[] {
			mRowTypeDirty,
			mTitleDirty,
			mDescriptionDirty,
			mIngredientQuantityDirty,
			mSortKeyDirty
		});
	}
	
	@Override
	protected AbstractValuesBuilder createBuilder() {
		Builder builder = RecipesAndIngredients.newBuilder();

		if(mRowTypeDirty) {
			builder.setRowType(mRowType);
		}
		if(mTitleDirty) {
			builder.setTitle(mTitle);
		}
		if(mDescriptionDirty) {
			builder.setDescription(mDescription);
		}
		if(mIngredientQuantityDirty) {
			builder.setIngredientQuantity(mIngredientQuantity);
		}
		if(mSortKeyDirty) {
			builder.setSortKey(mSortKey);
		}
		
		return builder;
	}
	
    @Override
	public void makeDirty(boolean dirty){
		mRowTypeDirty = dirty;
		mTitleDirty = dirty;
		mDescriptionDirty = dirty;
		mIngredientQuantityDirty = dirty;
		mSortKeyDirty = dirty;
	}

	@Override
	protected void setPropertiesFromCursor(Cursor c) {
		setId(c.getLong(Indices._ID));
		setRowType(c.getString(Indices.ROW_TYPE));
		setTitle(c.getString(Indices.TITLE));
		setDescription(c.getString(Indices.DESCRIPTION));
		setIngredientQuantity(c.getString(Indices.INGREDIENT_QUANTITY));
		setSortKey(c.getString(Indices.SORT_KEY));
	}
	
	public static RecipesAndIngredientsRecord fromCursor(Cursor c) {
	    RecipesAndIngredientsRecord item = new RecipesAndIngredientsRecord();
	    
		item.setPropertiesFromCursor(c);
		
		item.makeDirty(false);
		
	    return item;
	}
	
	public static RecipesAndIngredientsRecord get(long id) {
	    Cursor c = null;
	    
	    ContentResolver resolver = Mechanoid.getContentResolver();
	    
	    try {
	        c = resolver.query(RecipesAndIngredients.CONTENT_URI.buildUpon()
			.appendPath(String.valueOf(id)).build(), PROJECTION, null, null, null);
	        
	        if(!c.moveToFirst()) {
	            return null;
	        }
	        
	        return fromCursor(c);
	    } finally {
	        Closeables.closeSilently(c);
	    }
	}
}