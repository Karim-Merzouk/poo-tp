# Model Overview

This project models a smart agriculture and livestock monitoring system. It combines zones, sensors, readings, and alerts to track crops, animals, and aquaculture activity.

Some methods in the Java classes are only partial implementations or placeholders. This document explains the intended role of each type based on the current workspace code.

## Core Enums

### StatusZone
Represents the operational state of a zone.

- ACTIVE: the zone is operating normally.
- SUSPENDED: the zone is temporarily inactive.

### HealthStatus
Represents the health condition of an animal.

- HEALTHY: no known health issue.
- SICK: the animal has a health problem.
- QUARANTINED: the animal is isolated for safety.

### Family
Represents the broad livestock family of an animal.

- RUMINANT: animals such as cattle or sheep.
- POULTRY: birds such as chickens.

### CropFamily
Represents the type of crop.

- CEREAL: grain crops.
- VEGETABLE: vegetable crops.
- FRUIT: fruit crops.

### GrowthStatus
Represents the growth stage of a crop.

- SOWING: seeds have been planted.
- GERMINATION: seeds are sprouting.
- GROWTH: plant is actively growing.
- MATURITY: plant has reached maturity.
- HARVEST: crop is ready to harvest.

### StatusSensor
Represents the state of a sensor.

- ACTIVE: sensor is working normally.
- FAULTY: sensor is broken or unreliable.
- SUSPENDED: sensor is temporarily inactive.

### SType
Represents the sensor category.

- ENVIRONMENTAL: monitors environmental conditions.
- SOIL: monitors soil properties.
- BIOMETRIC: monitors animal biometric data.
- GPS: monitors location.
- WATER: monitors water conditions.

### ReadingStatus
Represents the interpretation of a sensor reading.

- NORMAL: value is within expected bounds.
- WARNING: value is close to a limit.
- CRITICAL: value is outside safe limits.

### SeverityLevel
Represents the severity of an alert.

- WARNING: attention is needed.
- CRITICAL: immediate action is needed.

## Interfaces

### FeedingProgram
Defines feeding information for zones that manage animals.

Methods:

- getFeedType(): returns the type of feed.
- getQuantityPerMeal(): returns the quantity given per meal.
- getSchedule(): returns the feeding schedule.

Used by:

- LivestockZone
- AquacultureZone

### Range
Defines a numeric threshold range.

Methods:

- getMin(): returns the lower bound.
- getMax(): returns the upper bound.
- isInRange(double value): checks whether a value belongs to the range.

Used by:

- Sensor, as the threshold contract for sensor limits.

## Entity and Domain Classes

### Tank
Represents a tank used in aquaculture.

Fields:

- id: tank identifier.
- capacity: tank capacity.

Methods:

- getId(): returns the tank id.
- getCapacity(): returns the tank capacity.

Role:

- Associated with AquacultureZone to store the physical resource used by aquatic production.

### Crop
Represents a crop being cultivated in a crop zone.

Fields:

- name: crop name.
- cropFamily: crop category.
- plantingDate: planting date.
- harvestDate: expected or actual harvest date.
- growthStatus: current growth stage.
- optimalPHMin / optimalPHMax: acceptable pH interval.
- optimalMoistureMin / optimalMoistureMax: acceptable moisture interval.

Methods:

- updateGrowthStage(GrowthStatus g): updates the current stage.
- getGrowthStatus(): returns the current stage.
- getName(): returns the crop name.

Role:

- Captures both identity and agronomic requirements for crop monitoring.

### Animal
Represents a livestock animal.

Fields:

- id: animal identifier.
- species: animal species.
- age: age in years or another unit used by the application.
- weight: current weight.
- healthStatus: current health condition.
- family: livestock family.

Methods:

- updateWeight(double w): updates the animal weight.
- logIllness(String desc): records an illness note.
- getHealthStatus(): returns the current health status.
- getId(): returns the animal id.

Role:

- Basic record for cattle, poultry, and other managed animals.

### AquaAnimal
Represents a fish or other aquatic organism count by species.

Fields:

- species: aquatic species.
- count: number of animals of that species.

Methods:

- getSpecies(): returns the species name.
- getCount(): returns the count.

Role:

- Lightweight aggregate used for aquaculture-related tracking.

## Zone Hierarchy

### Zone
Abstract base class for all production zones.

Fields:

- code: unique zone code.
- name: zone name.
- status: current zone state.
- sensors: collection of sensors assigned to the zone.

Methods:

- suspend(): marks the zone as suspended.
- reactivate(): marks the zone as active again.
- addSensor(Sensor s): attaches a sensor to the zone.
- getSensors(): returns all sensors in the zone.
- recordProduction(): abstract operation for production logging.

Role:

- Common parent for crop, livestock, and aquaculture zones.

### CropZone
Represents a zone dedicated to crops.

Fields:

- crops: list of crops in the zone.
- cropYield: production yield for the zone.

Methods:

- addCrop(Crop c): adds a crop to the zone.
- getCrops(): returns the list of crops.
- generateStatusReport(): returns a status summary.
- recordProduction(): records crop production.

Role:

- Manages agronomic production and crop collection data.

### LivestockZone
Abstract base class for livestock zones.

Fields:

- animals: list of animals managed in the zone.

Methods:

- addAnimal(Animal a): adds an animal to the zone.
- getAnimals(): returns the animals in the zone.
- getFeedingProgram(): returns the feeding program view of the zone.

Role:

- Common parent for ruminant and poultry zones.
- Also acts as a feeding-program provider in the current code.

### RuminantZone
Represents a livestock zone for ruminants.

Fields:

- milkYield: amount of milk produced.

Methods:

- getMilkYield(): returns the milk yield.
- recordProduction(): records production.
- getFeedType(): returns the feed type.
- getQuantityPerMeal(): returns feed quantity per meal.
- getSchedule(): returns feeding schedule.

Role:

- Specializes livestock management for dairy or ruminant animals.

### PoultryZone
Represents a livestock zone for poultry.

Fields:

- eggCount: number of eggs produced.

Methods:

- getEggCount(): returns the egg count.
- recordProduction(): records production.
- getFeedType(): returns the feed type.
- getQuantityPerMeal(): returns feed quantity per meal.
- getSchedule(): returns feeding schedule.

Role:

- Specializes livestock management for egg-producing birds.

### AquacultureZone
Represents a zone for aquatic production.

Fields:

- tank: tank assigned to the zone.
- animalCount: number of aquatic animals.
- species: species being raised.
- harvestWeight: expected or accumulated harvest weight.

Methods:

- getTank(): returns the associated tank.
- recordProduction(): records aquaculture production.
- getFeedType(): returns the feed type.
- getQuantityPerMeal(): returns feed quantity per meal.
- getSchedule(): returns feeding schedule.

Role:

- Models fish or similar aquatic livestock operations.

## Sensor Hierarchy

### Sensor
Abstract base class for all sensors.

Fields:

- code: sensor identifier.
- status: sensor state.
- sensorType: sensor category.
- zone: zone to which the sensor belongs.
- thresholdRange: numeric threshold policy for the sensor.

Methods:

- activate(): sets the sensor to active.
- suspend(): sets the sensor to suspended.
- setFaulty(): sets the sensor to faulty.
- getCode(): returns the sensor code.
- getStatus(): returns the sensor status.
- sendReading(): abstract operation that creates or sends a reading.
- getMin(): returns the minimum threshold.
- getMax(): returns the maximum threshold.
- isInRange(double value): checks whether a value is within threshold.

Role:

- Shared parent for environmental, soil, biometric, water, and GPS sensors.

### EnvironmentSensor
Sensor dedicated to environmental measurements.

Methods:

- sendReading(): produces a reading.

Role:

- Used for climate or ambient conditions such as temperature or humidity.

### SoilSensor
Sensor dedicated to soil measurements.

Methods:

- sendReading(): produces a reading.

Role:

- Used for soil-related metrics such as moisture or pH.

### BiometricSensor
Sensor dedicated to biometric measurements.

Methods:

- sendReading(): produces a reading.

Role:

- Used to monitor animal health-related signals.

### WaterSensor
Sensor dedicated to water measurements.

Methods:

- sendReading(): produces a reading.

Role:

- Used for aquaculture or water-quality monitoring.

### GPSCollar
GPS sensor attached to an animal.

Fields:

- animal: the animal being tracked.

Methods:

- getAnimal(): returns the tracked animal.
- sendReading(): produces a GPS reading.

Role:

- Tracks the position of a specific animal.

## Reading Hierarchy

### Reading
Abstract base class for all sensor readings.

Fields:

- id: reading identifier.
- timestamp: creation time.
- sensor: sensor that produced the reading.
- readingStatus: interpretation of the reading.

Methods:

- getId(): returns the reading id.
- getTimestamp(): returns the timestamp.
- getSensor(): returns the source sensor.
- checkThreshold(): checks whether the reading is inside thresholds.
- generateAlert(): creates an alert when needed.
- getReadingStatus(): returns the reading status.

Role:

- Common parent for numeric and GPS readings.

### NumericalReading
Represents a reading with a numeric value.

Fields:

- value: measured number.
- unit: measurement unit.

Methods:

- getValue(): returns the numeric value.
- getUnit(): returns the unit.

Role:

- Used for sensor data such as temperature, humidity, or weight.

### GPSReading
Represents a location reading.

Fields:

- latitude: latitude coordinate.
- longitude: longitude coordinate.

Methods:

- getLatitude(): returns latitude.
- getLongitude(): returns longitude.
- isOutOfZone(Zone z): checks whether the position is outside a zone.

Role:

- Used for location tracking through GPS collars.

## Alert Hierarchy

### Alert
Abstract base class for alerts raised by readings.

Fields:

- id: alert identifier.
- triggeredAt: time when the alert was triggered.
- severity: alert severity.
- triggerReading: reading that caused the alert.
- acknowledged: indicates whether someone has acknowledged the alert.

Methods:

- acknowledge(): marks the alert as acknowledged.
- dismiss(): dismisses the alert state.
- getSeverity(): returns the alert severity.
- getTriggerReading(): returns the triggering reading.
- isAcknowledged(): returns whether the alert has been acknowledged.

Role:

- Common parent for all alert types in the system.

### ThresholdAlert
Alert created when a numeric reading exceeds a threshold.

Methods:

- getExceededValue(): returns the value that exceeded the threshold.
- getThresholdRange(): returns the sensor threshold range.

Role:

- Used for environmental, soil, water, or biometric readings that move outside safe limits.

### GPSAlert
Alert created for a GPS-related issue.

Methods:

- getAnimal(): returns the tracked animal.
- getLastKnownPosition(): returns the GPS reading that triggered the alert.

Role:

- Used when an animal leaves a safe zone or its location needs attention.

## Relationship Summary

- Zone is the common parent of CropZone, LivestockZone, and AquacultureZone.
- LivestockZone is the parent of RuminantZone and PoultryZone.
- Sensor is the common parent of EnvironmentSensor, SoilSensor, BiometricSensor, WaterSensor, and GPSCollar.
- Reading is the common parent of NumericalReading and GPSReading.
- Alert is the common parent of ThresholdAlert and GPSAlert.
- FeedingProgram is implemented by livestock-related zone types in the code.
- Range is implemented by Sensor in the current code.

## Fidelity Notes for the Diagram

The diagram is structurally close to the workspace, but it is not fully faithful.

- The diagram uses some generic alert names that do not match the actual class names in the workspace.
- AquaAnimal exists as a class in code, but it is not shown with a clear role in the same way as the other major entities.
- Some methods in the code are placeholders and do not yet implement real business logic, so the diagram can only describe their intended behavior.
- The inheritance and aggregation backbone is mostly correct, but some labels and visual naming choices in the rendered diagram are looser than the code.

## Practical Reading of the Model

If you look at the system from top to bottom, the intended flow is:

1. A zone owns sensors and domain entities such as crops, animals, or a tank.
2. A sensor produces a reading.
3. A reading is checked against thresholds or location rules.
4. If the reading is problematic, an alert is generated.
5. The alert can then be acknowledged or dismissed.

This gives the model a clean monitoring pipeline for agriculture, livestock, and aquaculture.