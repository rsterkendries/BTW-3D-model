# Three-dimensional Bak-Tang-Wiesenfeld Model

This model was originally intended to give an explanation why lots of large systems in nature display powerlaw distributions phenomena. The BTW model is very simple and reproduces powerlaw distributions within the bulk region of its implementation as it can be seen on the screenshot below. Inevitable size effects appear at the extremities of the distribution. This is due to the borders of the sample where avalanche cannot freely spread as it would do in the bulk region.

# Simple Usage

```
int size = 10; // sample size
BTW_3D btw = new BTW_3D(size); //construct instance
int xp = 100; // number of experiments (distribution is an average of results)
btw.startAvalanche(xp); //compute distribution
TreeMap<Integer,Double> = btw.getRelativeDistribution(); //retrieve distribution
```

# Screenshot
Screenshot of the distribution produced by TestClass.java (need `jfreechart` library)

![example](https://github.com/rsterkendries/BTW-3D-model/blob/master/images/average_1000xp_on_16x16x16_grid.png)
