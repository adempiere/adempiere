-- Jul 16, 2012 10:56:47 AM CDT
-- MFG-25
INSERT INTO PP_ForecastRule (AD_Client_ID,AD_Org_ID,CalculationClass,Created,CreatedBy,Description,IsActive,Name,PP_ForecastRule_ID,Updated,UpdatedBy) VALUES (0,0,'BestForcastModel',TO_TIMESTAMP('2012-07-16 10:56:46','YYYY-MM-DD HH24:MI:SS'),100,'Best Forcast Model','Y','Best Forcast Model',50007,TO_TIMESTAMP('2012-07-16 10:56:46','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2012 10:57:13 AM CDT
-- MFG-25
INSERT INTO AD_Column (AD_Client_ID,AD_Column_ID,AD_Element_ID,AD_Org_ID,AD_Reference_ID,AD_Table_ID,ColumnName,Created,CreatedBy,Description,EntityType,FieldLength,Help,IsActive,IsAllowLogging,IsAlwaysUpdateable,IsAutocomplete,IsEncrypted,IsIdentifier,IsKey,IsMandatory,IsParent,IsSelectionColumn,IsSyncDatabase,IsTranslated,IsUpdateable,Name,SeqNo,Updated,UpdatedBy,Version) VALUES (0,63683,326,0,14,53396,'Help',TO_TIMESTAMP('2012-07-16 10:57:12','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint','EE01',2000,'The Help field contains a hint, comment or help about the use of this item.','Y','Y','N','N','N','N','N','N','N','N','N','N','Y','Comment/Help',0,TO_TIMESTAMP('2012-07-16 10:57:12','YYYY-MM-DD HH24:MI:SS'),100,0)
;

-- Jul 16, 2012 10:57:13 AM CDT
-- MFG-25
INSERT INTO AD_Column_Trl (AD_Language,AD_Column_ID, Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Column_ID, t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Column t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Column_ID=63683 AND NOT EXISTS (SELECT * FROM AD_Column_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Column_ID=t.AD_Column_ID)
;

-- Jul 16, 2012 10:57:16 AM CDT
-- MFG-25
ALTER TABLE PP_ForecastRule ADD COLUMN Help VARCHAR(2000) DEFAULT NULL 
;

-- Jul 16, 2012 11:51:46 AM CDT
-- MFG-25
UPDATE AD_Column SET AD_Reference_ID=36, FieldLength=0,Updated=TO_DATE('2012-07-16 11:51:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Column_ID=63683
;

-- Jul 16, 2012 10:57:44 AM CDT
-- MFG-25
INSERT INTO AD_Field (AD_Client_ID,AD_Column_ID,AD_Field_ID,AD_Org_ID,AD_Tab_ID,Created,CreatedBy,Description,DisplayLength,EntityType,Help,IsActive,IsCentrallyMaintained,IsDisplayed,IsEncrypted,IsFieldOnly,IsHeading,IsReadOnly,IsSameLine,Name,Updated,UpdatedBy) VALUES (0,63683,64723,0,53526,TO_TIMESTAMP('2012-07-16 10:57:43','YYYY-MM-DD HH24:MI:SS'),100,'Comment or Hint',2000,'EE01','The Help field contains a hint, comment or help about the use of this item.','Y','Y','Y','N','N','N','N','N','Comment/Help',TO_TIMESTAMP('2012-07-16 10:57:43','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2012 10:57:44 AM CDT
-- MFG-25
INSERT INTO AD_Field_Trl (AD_Language,AD_Field_ID, Description,Help,Name, IsTranslated,AD_Client_ID,AD_Org_ID,Created,Createdby,Updated,UpdatedBy) SELECT l.AD_Language,t.AD_Field_ID, t.Description,t.Help,t.Name, 'N',t.AD_Client_ID,t.AD_Org_ID,t.Created,t.Createdby,t.Updated,t.UpdatedBy FROM AD_Language l, AD_Field t WHERE l.IsActive='Y' AND l.IsSystemLanguage='Y' AND l.IsBaseLanguage='N' AND t.AD_Field_ID=64723 AND NOT EXISTS (SELECT * FROM AD_Field_Trl tt WHERE tt.AD_Language=l.AD_Language AND tt.AD_Field_ID=t.AD_Field_ID)
;

-- Jul 16, 2012 10:57:51 AM CDT
-- MFG-25
UPDATE AD_Field SET SeqNo=50,IsDisplayed='Y' WHERE AD_Field_ID=64723
;

-- Jul 16, 2012 10:57:51 AM CDT
-- MFG-25
UPDATE AD_Field SET SeqNo=60,IsDisplayed='Y' WHERE AD_Field_ID=64561
;

-- Jul 16, 2012 10:57:51 AM CDT
-- MFG-25
UPDATE AD_Field SET SeqNo=70,IsDisplayed='Y' WHERE AD_Field_ID=64562
;

-- Jul 16, 2012 10:46:59 AM CDT
-- MFG-25
UPDATE AD_Table SET AccessLevel='7',Updated=TO_TIMESTAMP('2012-07-16 10:46:59','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE AD_Table_ID=53396
;

-- Jul 16, 2012 10:48:42 AM CDT
-- MFG-25
INSERT INTO PP_ForecastRule (AD_Client_ID,AD_Org_ID,CalculationClass,Created,CreatedBy,Description,IsActive,Name,PP_ForecastRule_ID,Updated,UpdatedBy) VALUES (0,0,'DoubleExponentialSmoothing',TO_TIMESTAMP('2012-07-16 10:48:41','YYYY-MM-DD HH24:MI:SS'),100,'Double Exponential Smoothing','Y','Double Exponential Smoothing',50000,TO_TIMESTAMP('2012-07-16 10:48:41','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2012 10:49:50 AM CDT
-- MFG-25
INSERT INTO PP_ForecastRule (AD_Client_ID,AD_Org_ID,CalculationClass,Created,CreatedBy,Description,IsActive,Name,PP_ForecastRule_ID,Updated,UpdatedBy) VALUES (0,0,'SimpleExponentialSmoothing',TO_TIMESTAMP('2012-07-16 10:49:49','YYYY-MM-DD HH24:MI:SS'),100,'Simple Expnential Smoothing','Y','Simple Expnential Smoothing',50001,TO_TIMESTAMP('2012-07-16 10:49:49','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2012 10:50:37 AM CDT
-- MFG-25
INSERT INTO PP_ForecastRule (AD_Client_ID,AD_Org_ID,CalculationClass,Created,CreatedBy,Description,IsActive,Name,PP_ForecastRule_ID,Updated,UpdatedBy) VALUES (0,0,'TripleExponentialSmoothing',TO_TIMESTAMP('2012-07-16 10:50:36','YYYY-MM-DD HH24:MI:SS'),100,'Triple Exponential Smoothing','Y','Triple Exponential Smoothing',50002,TO_TIMESTAMP('2012-07-16 10:50:36','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2012 10:51:56 AM CDT
-- MFG-25
INSERT INTO PP_ForecastRule (AD_Client_ID,AD_Org_ID,CalculationClass,Created,CreatedBy,Description,IsActive,Name,PP_ForecastRule_ID,Updated,UpdatedBy) VALUES (0,0,'PolynomialRegression',TO_TIMESTAMP('2012-07-16 10:51:55','YYYY-MM-DD HH24:MI:SS'),100,'Polynomial Regression','Y','Polynomial Regression',50003,TO_TIMESTAMP('2012-07-16 10:51:55','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2012 10:52:39 AM CDT
-- MFG-25
INSERT INTO PP_ForecastRule (AD_Client_ID,AD_Org_ID,CalculationClass,Created,CreatedBy,Description,IsActive,Name,PP_ForecastRule_ID,Updated,UpdatedBy) VALUES (0,0,'Regression',TO_TIMESTAMP('2012-07-16 10:52:38','YYYY-MM-DD HH24:MI:SS'),100,'Regression','Y','Regression',50004,TO_TIMESTAMP('2012-07-16 10:52:38','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2012 10:54:33 AM CDT
-- MFG-25
INSERT INTO PP_ForecastRule (AD_Client_ID,AD_Org_ID,CalculationClass,Created,CreatedBy,Description,IsActive,Name,PP_ForecastRule_ID,Updated,UpdatedBy) VALUES (0,0,'NaiveForecasting',TO_TIMESTAMP('2012-07-16 10:54:32','YYYY-MM-DD HH24:MI:SS'),100,'Naive Forecasting','Y','Naive Forecasting',50005,TO_TIMESTAMP('2012-07-16 10:54:32','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2012 10:55:35 AM CDT
-- MFG-25
INSERT INTO PP_ForecastRule (AD_Client_ID,AD_Org_ID,CalculationClass,Created,CreatedBy,Description,IsActive,Name,PP_ForecastRule_ID,Updated,UpdatedBy) VALUES (0,0,'MultipleLinearRegression',TO_TIMESTAMP('2012-07-16 10:55:34','YYYY-MM-DD HH24:MI:SS'),100,'Multiple MultipleLinear Regression','Y','Multiple Linear Regression',50006,TO_TIMESTAMP('2012-07-16 10:55:34','YYYY-MM-DD HH24:MI:SS'),100)
;

-- Jul 16, 2012 10:55:47 AM CDT
-- MFG-25
UPDATE PP_ForecastRule SET Description='Multiple Linear Regression',Updated=TO_TIMESTAMP('2012-07-16 10:55:47','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_ForecastRule_ID=50006
;


-- Jul 16, 2012 11:01:26 AM CDT
-- MFG-25
UPDATE PP_ForecastRule SET Help='Double exponential smoothing - also known as Holt exponential smoothing
 - is a refinement of the popular simple exponential smoothing model but
 adds another component which takes into account any trend in the data.
 Simple exponential smoothing models work best with data where there are no
 trend or seasonality components to the data. When the data exhibits either
 an increasing or decreasing trend over time, simple exponential smoothing
 forecasts tend to lag behind observations. Double exponential smoothing is
 designed to address this type of data series by taking into account any
 trend in the data.
 <br>
 <p>Note that double exponential smoothing still does not address
 seasonality. For better exponentially smoothed forecasts using data where
 there is expected or known to be seasonal variation in the data, use triple
 exponential smoothing.
  <br>
 <p>As with simple exponential smoothing, in double exponential smoothing
 models past observations are given exponentially smaller weights as the
 observations get older. In other words, recent observations are given
 relatively more weight in forecasting than the older observations.
<p><a href="http://www.itl.nist.gov/div898/handbook/pmc/section4/pmc433.htm">Engineering Statistics Handbook, 6.4.3.3 Double Exponential Smoothing</a>

<p>Author Steven R. Gould',Updated=TO_TIMESTAMP('2012-07-16 11:01:26','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_ForecastRule_ID=50000
;

-- Jul 16, 2012 11:05:21 AM CDT
-- MFG-25
UPDATE PP_ForecastRule SET Help='A simple exponential smoothing forecast model is a very popular model
 used to produce a smoothed Time Series. Whereas in simple Moving Average
 models the past observations are weighted equally, Exponential Smoothing
 assigns exponentially decreasing weights as the observations get older.
<p>In other words, recent observations are given relatively more weight
 in forecasting than the older observations.
<p>In the case of moving averages, the weights assigned to the
observations are the same and are equal to <sup>1</sup>/<sub>N</sub>. In
simple exponential smoothing, however, a "smoothing parameter" - or
"smoothing constant" - is used to determine the weights assigned to the
observations.
<p>This simple exponential smoothing model begins by setting the forecast
for the second period equal to the observation of the first period. Note
that there are ways of initializing the model. As of the time of writing,
these alternatives are not available in this implementation. Future
implementations of this model may offer these options.
<h2>Choosing a smoothing constant</h2>
<p>The smoothing constant must be a value in the range 0.0-1.0. But, what
is the "best" value to use for the smoothing constant? This depends on the
data series being modeled. The speed at which the older responses are
dampened (smoothed) is a function of the value of the smoothing constant.
When this smoothing constant is close to 1.0, dampening is quick - more
weight is given to recent observations - and when it is close to 0.0,
dampening is slow - and relatively less weight is given to recent
observations.
<p>The best value for the smoothing constant is the one that results in the
smallest mean of the squared errors (or other similar accuracy indicator).
<p><a href="http://www.itl.nist.gov/div898/handbook/pmc/section4/pmc431.htm">Engineering Statistics Handbook, 6.4.3.1 Simple Expnential Smoothing</a>
<p>Steven R. Gould',Updated=TO_DATE('2012-07-16 11:05:21','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_ForecastRule_ID=50001
;

-- Jul 16, 2012 11:08:53 AM CDT
-- MFG-25
UPDATE PP_ForecastRule SET Help='Triple exponential smoothing - also known as the Winters method - is a
refinement of the popular double exponential smoothing model but adds
another component which takes into account any seasonality - or periodicity
- in the data.

<p>Simple exponential smoothing models work best with data where there are
no trend or seasonality components to the data. When the data exhibits
either an increasing or decreasing trend over time, simple exponential
smoothing forecasts tend to lag behind observations. Double exponential
smoothing is designed to address this type of data series by taking into
account any trend in the data. However, neither of these exponential
smoothing models address any seasonality in the data.

<p>For better exponentially smoothed forecasts of data where there is
expected or known to be seasonal variation in the data, use triple
exponential smoothing.

<p>As with simple exponential smoothing, in triple exponential smoothing
models past observations are given exponentially smaller weights as the
observations get older. In other words, recent observations are given
relatively more weight in forecasting than the older observations. This is
true for all terms involved - namely, the base level
<code>L<sub>t</sub></code>, the trend <code>T<sub>t</sub></code> as well as
the seasonality index <code>s<sub>t</sub></code>.
<p><a href="http://www.itl.nist.gov/div898/handbook/pmc/section4/pmc435.htm">Engineering Statistics Handbook, 6.4.3.5 Triple Exponential Smoothing</a>
<p>Author Steven R. Gould
',Updated=TO_TIMESTAMP('2012-07-16 11:08:53','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_ForecastRule_ID=50002
;

-- Jul 16, 2012 11:12:56 AM CDT
-- MFG-25
UPDATE PP_ForecastRule SET Help='Implements a single variable polynomial regression model using the variable
named in the constructor as the independent variable. The cofficients of
the regression as well as the accuracy indicators are determined from the
data set passed to init.

<p>Once initialized, this model can be applied to another data set using
the forecast method to forecast values of the dependent variable based on
values of the dependent variable (the one named in the constructor).

<p>A single variable polynomial regression model essentially attempts to
put a polynomial line - a curve if you prefer - through the data points.
Mathematically, assuming the independent variable is x and the dependent
variable is y, then this line can be represented as:

<pre>y = a<sub>0</sub> + a<sub>1</sub>*x + a<sub>2</sub>*x<sup>2</sup> + a<sub>3</sub>*x<sup>3</sup> + ... + a<sub>m</sub>*x<sup>m</sup></pre>

You can specify the order of the polynomial fit (the value of
<code>m</code> in the above equation) in the constructor.
<p> Author Steven R. Gould',Updated=TO_TIMESTAMP('2012-07-16 11:12:56','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_ForecastRule_ID=50003
;

-- Jul 16, 2012 11:14:15 AM CDT
-- MFG-25
UPDATE PP_ForecastRule SET Help='Implements a single variable linear regression model using the variable
named in the constructor as the independent variable. The cofficients of
the regression - the intercept and the slope - as well as the accuracy
indicators are determined from the data set passed to init.

<p>Once initialized, this model can be applied to another data set using
the forecast method to forecast values of the dependent variable based on
values of the dependent variable (the one named in the constructor).

<p>A single variable linear regression model essentially attempts to put a
straight line through the data points. For the more mathematically inclined,
this line is defined by its gradient or slope, and the point at which it
intercepts the x-axis (i.e. where the independent variable has, perhaps only
theoretically, a value of zero). Mathematically, assuming the independent
variable is x and the dependent variable is y, then this line can be
represented as: <pre>y = intercept + slope * x</pre>
<p>@author Steven R. Gould
',Updated=TO_TIMESTAMP('2012-07-16 11:14:15','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_ForecastRule_ID=50004
;

-- Jul 16, 2012 11:15:46 AM CDT
-- MFG-25
UPDATE PP_ForecastRule SET Help='A naive forecasting model is a special case of the moving average
forecasting model where the number of periods used for smoothing is 1.
Therefore, the forecast for a period, t, is simply the observed value
for the previous period, t-1.

<p>Due to the simplistic nature of the naive forecasting model, it can only
be used to forecast up to one period in the future. It is not at all useful
as a medium-long range forecasting tool.

<p>This model really is a simplistic model, and is included partly
for completeness and partly because of its simplicity. It is unlikely that
you''ll want to use this model directly. Instead, consider using either the
moving average model, or the more general weighted moving average model
with a higher (i.e. greater than 1) number of periods, and possibly a
different set of weights.

<p>Author Steven R. Gould',Updated=TO_TIMESTAMP('2012-07-16 11:15:46','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_ForecastRule_ID=50005
;

-- Jul 16, 2012 11:17:28 AM CDT
-- MFG-25
UPDATE PP_ForecastRule SET Help='Implements a multiple variable linear regression model using the variables
named in the constructor as the independent variables, or the variables
passed into one of the init methods. The cofficients of the regression, as
well as the accuracy indicators are determined from the data set passed to
init.

<p>Once initialized, this model can be applied to another data set using
the forecast method to forecast values of the dependent variable based on
values of the dependent variable (the one named in the constructor).

<p>A multiple variable linear regression model essentially attempts to put
a hyperplane through the data points. Mathematically, assuming the
independent variables are x<sub>i</sub> and the dependent variable is y,
then this hyperplane can be represented as:
<pre>y = a<sub>0</sub> + a<sub>1</sub>*x<sub>1</sub> + a<sub>2</sub>*x<sub>2</sub> + a<sub>3</sub>*x<sub>3</sub> + ...</pre>
where the a<sub>i</sub> are the coefficients of the regression. The
coefficient a<sub>0</sub> is also referred to as the intercept. If all
x<sub>i</sub> were zero (theoretically at least), it is the forecast value
of the dependentVariable, y.

<p>Author Steven R. Gould',Updated=TO_TIMESTAMP('2012-07-16 11:17:28','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_ForecastRule_ID=50006
;

-- Jul 16, 2012 11:18:42 AM CDT
-- MFG-25
UPDATE PP_ForecastRule SET Help='The Forecaster class is a factory class that obtains the best
ForecastingModel for the given data set. The interpretation of the "best"
forecasting model can be user selected (bias, MAD, MAPE, MSE, SAE or a
blend of these), or left up to the Forecaster. If the interpretation is left
up to the Forecaster class then it will evaluate a combination of these other
measures and comes up with somewhat of a concensus opinion as the to best
model
<p>Author Steven R. Gould',Updated=TO_TIMESTAMP('2012-07-16 11:18:42','YYYY-MM-DD HH24:MI:SS'),UpdatedBy=100 WHERE PP_ForecastRule_ID=50007
;

