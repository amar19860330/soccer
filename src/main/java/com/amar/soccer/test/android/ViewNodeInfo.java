package com.amar.soccer.test.android;

import java.util.Map;

import com.amar.soccer.util.MathUtil;
import com.android.hierarchyviewerlib.models.ViewNode;
import com.android.hierarchyviewerlib.models.ViewNode.Property;

public class ViewNodeInfo
{

	public enum Type
	{
		Text, Button, DropDownList, TextArea
	}

	public static final String DATA_TEXT = "text:mText";

	public static final String DATA_LEFT = "drawing:getX()";

	public static final String DATA_TOP = "drawing:getY()";

	public static final String DATA_LEFT_SCREEN = "layout:getLocationOnScreen_x()";

	public static final String DATA_TOP_SCREEN = "layout:getLocationOnScreen_y()";

	public static final String DATA_WIDTH_MEASURED = "measurement:mMeasuredWidth";

	public static final String DATA_HEIGHT_MEASURED = "measurement:mMeasuredHeight";

	public static final String TYPE_STRING_EditText = "android.widget.EditText";

	private int x;

	private int y;

	private int width;

	private int height;

	private String id;

	private Type type;

	private String typeString;

	private String text;

	private int size;

	private int actionHeight;

	private ViewNode viewNode;

	public static Type analyzeType( String typeString )
	{
		// todo
		return null;
	}

	public ViewNodeInfo( ViewNode viewNode )
	{
		this.viewNode = viewNode;
		init( viewNode );
	}

	public boolean inThisArea( int area_x , int area_y )
	{
		return MathUtil.inThisArea( area_x , area_y - actionHeight , x , y , width , height );
	}

	private void init( ViewNode viewNode )
	{
		setSize( viewNode );
		setType( viewNode );
		setOtherInfo( viewNode );
	}

	private void setOtherInfo( ViewNode viewNode )
	{
		this.id = viewNode.id;
		if ( viewNode.namedProperties != null )
		{

		}
	}

	private void setType( ViewNode viewNode )
	{
		this.typeString = viewNode.name;
		this.type = analyzeType( typeString );
	}

	private void setSize( ViewNode viewNode )
	{
		if ( viewNode.namedProperties != null )
		{
			Map<String,Property> propertyMap = viewNode.namedProperties;
			boolean existScreenX = propertyMap.containsKey( DATA_LEFT_SCREEN );
			boolean existScreenY = propertyMap.containsKey( DATA_TOP_SCREEN );
			boolean existX = propertyMap.containsKey( DATA_LEFT );
			boolean existY = propertyMap.containsKey( DATA_TOP );
			boolean existWidth = propertyMap.containsKey( DATA_WIDTH_MEASURED );
			boolean existHeight = propertyMap.containsKey( DATA_HEIGHT_MEASURED );

			if ( ( existScreenX || existX ) && ( existScreenY || existY ) && existWidth && existHeight )
			{
				String x = existScreenX ? propertyMap.get( DATA_LEFT_SCREEN ).value : propertyMap.get( DATA_LEFT ).value;
				String y = existScreenY ? propertyMap.get( DATA_TOP_SCREEN ).value : propertyMap.get( DATA_TOP ).value;
				String width = propertyMap.get( DATA_WIDTH_MEASURED ).value;
				String height = propertyMap.get( DATA_HEIGHT_MEASURED ).value;
				this.x = ( int ) Float.parseFloat( x );
				this.y = ( int ) Float.parseFloat( y );
				this.width = ( int ) Float.parseFloat( width );
				this.height = ( int ) Float.parseFloat( height );
			}
		}
	}

	public int getX()
	{
		return x;
	}

	public void setX( int x )
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY( int y )
	{
		this.y = y;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth( int width )
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight( int height )
	{
		this.height = height;
	}

	public String getId()
	{
		return id;
	}

	public void setId( String id )
	{
		this.id = id;
	}

	public Type getType()
	{
		return type;
	}

	public void setType( Type type )
	{
		this.type = type;
	}

	public String getTypeString()
	{
		return typeString;
	}

	public void setTypeString( String typeString )
	{
		this.typeString = typeString;
	}

	public String getText()
	{
		return text;
	}

	public void setText( String text )
	{
		this.text = text;
	}

	public int getSize()
	{
		return size;
	}

	public void setSize( int size )
	{
		this.size = size;
	}

	public int getActionHeight()
	{
		return actionHeight;
	}

	public void setActionHeight( int actionHeight )
	{
		this.actionHeight = actionHeight;
	}

	public ViewNode getViewNode()
	{
		return viewNode;
	}

	public void setViewNode( ViewNode viewNode )
	{
		this.viewNode = viewNode;
	}

	@Override
	public String toString()
	{
		return "ViewNodeInfo [x=" + x + ", y=" + y + ", width=" + width + ", height=" + height + ", id=" + id + ", type=" + type + ", typeString=" + typeString + ", text=" + text + ", size=" + size
				+ ", actionHeight=" + actionHeight + "]";
	}

}
