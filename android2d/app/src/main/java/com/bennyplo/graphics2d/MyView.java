package com.bennyplo.graphics2d;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Shader;
import android.view.View;

/**
 * Created by benlo on 09/05/2018.
 */

public class MyView extends View {
    private Paint redPaint,bluePaint;
    private Path LineGraph;
    int viewHeight,viewWidth;
    Point[] points;
    private Path myLines;
    int plotData[]={11,29,10,20,12,5,29,24,21,13};

    public MyView(Context context) {
        super(context, null);
        //Add your initialisation code here
        redPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        redPaint.setStyle(Paint.Style.STROKE);//stroke only no fill
        redPaint.setColor(0xffff0000);//color red
        redPaint.setStrokeWidth(5);//set the line stroke width to 5
//        bluePaint=new Paint(Paint.ANTI_ALIAS_FLAG);
//        bluePaint.setStyle(Paint.Style.STROKE);//stroke only no fill
//        bluePaint.setColor(0xffff0000);//color red
//        redPaint.setStrokeWidth(5);//set the line stroke width to 5

        //for affine transformation
        points=new Point[5];
        points[0]=new Point(50,100);
        points[1]=new Point(150,400);
        points[2]=new Point(180,340);
        points[3]=new Point(240,420);
        points[4]=new Point(300,200);

        //to draw line graph
        viewHeight=getResources().getDisplayMetrics().heightPixels-70;
        viewWidth=getResources().getDisplayMetrics().widthPixels;
        LineGraph=createLineGraph(plotData,viewWidth,viewHeight);
    }

    //to update the parh after affine transformation
//    protected void updatePath(Point[] newpoints)
//    {
//        myLines.reset();
//        myLines.moveTo(newpoints[0].x,newpoints[0].y);
//        for(int i=1;i<newpoints.length;i++)
//        {
//            myLines.lineTo(newpoints[i].x,newpoints[i].y);
//        }
//        myLines.close();
//    }

    //to perform affine transformation
    protected Point[] affineTransformation(Point[] vertices, double[][] matrix) {
        Point[] result = new Point[vertices.length];
        for (int i = 0; i < vertices.length; i++) {
            int t = (int) (matrix[0][0] * vertices[i].x + matrix[0][1] * vertices[i].y + matrix[0][2]);
            int u = (int) (matrix[1][0] * vertices[i].x + matrix[1][1] * vertices[i].y + matrix[1][2]);
            result[i] = new Point(t, u);
        }
        return result;
    }

    //to perform translation
    protected Point[] translate(Point[] input,int px,int py)
    {
        double[][] matrix=new double[3][3];
        matrix[0][0]=1;matrix[0][1]=0;matrix[0][2]=px;
        matrix[1][0]=0;matrix[1][1]=1;matrix[1][2]=py;
        matrix[2][0]=0;matrix[2][1]=0;matrix[2][2]=1;
        return affineTransformation(input,matrix);
    }

    //for scaling
    protected Point[] scale(Point[] input,double sx,double sy)
    {
       double[][] matrix=new double[3][3];
        matrix[0][0]=sx;matrix[0][1]=0;matrix[0][2]=0;
        matrix[1][0]=0;matrix[1][1]=sy;matrix[1][2]=0;
        matrix[2][0]=0;matrix[2][1]=0;matrix[2][2]=1;
        return affineTransformation(input,matrix);
    }

    //to draw linegraph
    private Path createLineGraph(int[]input,int width,int height)
    {
        Point[] ptArray=new Point[input.length];
        int minValue=999999,maxValue=-999999;
        for(int i=0;i<input.length;i++) {
            ptArray[i] = new Point(i, input[i]);
            minValue = Math.min(minValue, input[i]);
            maxValue = Math.max(maxValue, input[i]);
        }
        ptArray=translate(ptArray,0,-minValue);
        double yScale=height/(double)(maxValue-minValue),xScale=width/(double)(input.length-1);
        ptArray=scale(ptArray,xScale,yScale);
        Path result=new Path();
        result.moveTo(ptArray[0].x,ptArray[0].y);
        for(int i=1;i<ptArray.length;i++)
        {
            result.lineTo(ptArray[i].x,ptArray[i].y);
        }
        return result;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //drawing different figures
        //canvas.drawRect(10,30,200,200,redPaint);
        //canvas.drawCircle(600,600,145,bluePaint);
        //canvas.drawCircle(500,450,50,bluePaint);
        //canvas.drawRect(500,500,700,700,redPaint);

        //drawing polylines and polygons
//        myLines=new Path();
//        myLines.moveTo(50,300);
//        myLines.lineTo(160,280);
//        myLines.lineTo(300,380);
//        myLines.lineTo(380,370);
//        myLines.lineTo(280,450);
       // myLines.lineTo(100,390);
       // myLines.lineTo(160,380);
       // myLines.lineTo(50,300);
       // Paint GreenPaint=new Paint();
        //GreenPaint.setARGB(250,0,255,0);
        //canvas.drawPath(myLines,GreenPaint);
        //canvas.drawCircle(211,364,250,redPaint);

        //to draw first object for transformation
//        myLines.moveTo(points[0].x,points[0].y);
//        for(int i=1;i<points.length;i++)
//        {
//            myLines.lineTo(points[i].x,points[i].y);
//        }
//        Paint gp=new Paint();
//        gp.setARGB(255,0,255,0);

        //drawing a polygon and both filling inside and stroke
        //Path Lines=new Path();
       // Lines.moveTo(50,500);
       // Lines.lineTo(160,480);
       // Lines.lineTo(300,580);
//        Lines.lineTo(380,570);
//        Lines.lineTo(280,650);
//        Lines.close();
//        Paint redFillPaint=new Paint();
//        redFillPaint.setStyle(Paint.Style.FILL);
//        redFillPaint.setARGB(255,255,0,0);
//        Paint blackPaint=new Paint();
//        blackPaint.setStyle(Paint.Style.STROKE);
//        blackPaint.setColor(Color.BLACK);
//        canvas.drawPath(Lines,redFillPaint);
//        canvas.drawPath(Lines,blackPaint);
//
//        //Linear grading of colors
//        LinearGradient lg=new LinearGradient(50,500,380,570,Color.RED,Color.BLUE, Shader.TileMode.MIRROR);
//        Paint gPaint=new Paint();
//        gPaint.setStyle(Paint.Style.FILL);
//        gPaint.setShader(lg);
        //canvas.drawPath(Lines,gPaint);

        //to draw linegraph
        canvas.drawPath(LineGraph,redPaint);

        //to draw object and translate it
//        canvas.drawPath(myLines,gp);
//        Point[] newPoints=translate(points,20,40);
//        updatePath(newPoints);
//        canvas.drawPath(myLines,redPaint);

    }
}
