# NMR-JSON
A new format for storing MRS(1D and 2D), MRSI, and Metabolite Basis-Set(1D and 2D): This format is based on JSON format which is a lightweight computer data interchange format. It contains the necessary information of an NMR experiment.
<!-- Copy and paste the converted output. -->

<!-----
NEW: Check the "Suppress top comment" option to remove this info from the output.

Conversion time: 1.552 seconds.


Using this Markdown file:

1. Paste this output into your source file.
2. See the notes and action items below regarding this conversion run.
3. Check the rendered output (headings, lists, code blocks, tables) for proper
   formatting and use a linkchecker before you publish this page.

Conversion notes:

* Docs to Markdown version 1.0β29
* Fri Nov 20 2020 08:55:41 GMT-0800 (PST)
* Source doc: A new format for storing simulated basis set:
* Tables are currently converted to HTML tables.
----->



## A new format for storing MRS(in-vivo and simulated _1D, 2D_,_ MRSI_, basis set) data and fitting models

This format is based on JSON format which is a lightweight computer data interchange format. It contains the necessary information of an NMR experiment.

In this format, data get stored as an N dimessional array. Like Nifti, the first three dimensions are reserved to define the three spatial dimensions — _x_, _y_ and _z_ —, while the fourth dimension is reserved to define the time — _t_. The dimensions, from fifth to N, are for user defined dimension (e.g. TE, TR , ...).

The table below shows each of the fields, their type, and a brief description. More details on how each field should be interpreted are provided further below.


<table>
  <tr>
   <td>Field
   </td>
   <td>Type
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td><code>Version</code>
   </td>
   <td><code>String</code>
   </td>
   <td>Version of NmrJson; not only there is a official version; but also every user can generate their own version.
   </td>
  </tr>
  <tr>
   <td><code>Name</code>
   </td>
   <td><code>String </code>
   </td>
   <td>Name of study
   </td>
  </tr>
  <tr>
   <td><code>StudyGroup</code>
   </td>
   <td><code>String</code>
   </td>
   <td>Connect a set of studies together
   </td>
  </tr>
  <tr>
   <td><code>ContentType</code>
   </td>
   <td><code>ContentType </code>
   </td>
   <td>The type of content can be one of the 2 following types: model and signal.
   </td>
  </tr>
  <tr>
   <td><code>Dimensions</code>
   </td>
   <td><code>Integer[]</code>
   </td>
   <td>Contains the size of  dimension of data. The dimensions 1, 2 and 3 are reserved to refer to space (x, y, z), the 4th dimension is assumed to refer to time/frequency, and the remaining dimensions can refer to user specific dimension (e.g. TE, TR , ...)
   </td>
  </tr>
  <tr>
   <td><code>DataType</code>
   </td>
   <td><code>DataType</code>
   </td>
   <td>Type of binary data
   </td>
  </tr>
  <tr>
   <td><code>ExperimentType</code>
   </td>
   <td><code>String</code>
   </td>
   <td>Type of experiment (e.g. SVS, CSI, Inversion Recovery)
   </td>
  </tr>
  <tr>
   <td><code>VoxelDimension</code>
   </td>
   <td><code>Float[3] </code>
   </td>
   <td>The size of voxel in spatial domain. (x, y, z)
   </td>
  </tr>
  <tr>
   <td><code>DataSlope</code>
   </td>
   <td><code>Float </code>
   </td>
   <td>Define a slope for a linear function
   </td>
  </tr>
  <tr>
   <td><code>DataOffset</code>
   </td>
   <td><code>Float </code>
   </td>
   <td>Define an offset for a linear function
   </td>
  </tr>
  <tr>
   <td><code>FieldStrength</code>
   </td>
   <td><code>Float </code>
   </td>
   <td>The strength of scanner field 
   </td>
  </tr>
  <tr>
   <td><code>DateofExperiment</code>
   </td>
   <td><code>String </code>
   </td>
   <td>The date of experiment
   </td>
  </tr>
  <tr>
   <td><code>PulseSequence</code>
   </td>
   <td><code>String </code>
   </td>
   <td>Pulse sequence of Experiment
   </td>
  </tr>
</table>


Another important feature is flags. These flags helps user to understand about the history and other information. The table below shows flags and their aims.


<table>
  <tr>
   <td>Field
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td><code>Boolean Binary</code>
   </td>
   <td>Indicate that data is storted as binary format or not
   </td>
  </tr>
  <tr>
   <td><code>Boolean Compressed</code>
   </td>
   <td>Indicate storing approach.This format can compress and reduce the binary data
   </td>
  </tr>
  <tr>
   <td><code>Boolean FrequecyDomain</code>
   </td>
   <td>Is the 4th dimension stores frequency spectra or not.
   </td>
  </tr>
  <tr>
   <td><code>Boolean Downsampled</code>
   </td>
   <td>Indicate that signals are down sampled or not
   </td>
  </tr>
  <tr>
   <td><code>Boolean Filtered</code>
   </td>
   <td>Indicate that signals are  filtered or not
   </td>
  </tr>
  <tr>
   <td><code>Boolean Zeropadded</code>
   </td>
   <td>Indicate that signal are zero padded or not
   </td>
  </tr>
  <tr>
   <td><code>Boolean Truncated</code>
   </td>
   <td>Indicate that signal are truncated or not
   </td>
  </tr>
  <tr>
   <td><code>Boolean FrequencyCorrected</code>
   </td>
   <td>Indicate that the frequency of signal are corrected or not
   </td>
  </tr>
  <tr>
   <td><code>Boolean PhaseCorrected</code>
   </td>
   <td>Indicate that the phase of signal are corrected or not
   </td>
  </tr>
</table>


There are two options for storing data. 



1. First, storing as two double arrays, namely real and imaginary signal, as two JSON array. It is not recommended in case the data has a huge volume. 
2. Second, we can store basis sets as two N-Dimensional matrix. A boolean flag, called `Binary `, indicates whether the second option is enabled or not. If it is true, the binary files, real and imaginary, get stored in the same directory with the following structure:

<table>
  <tr>
   <td>
File
   </td>
   <td>Name
   </td>
  </tr>
  <tr>
   <td>Real array
   </td>
   <td>(Json file name) + <code>_RD.nmrJson</code>
   </td>
  </tr>
  <tr>
   <td>Imaginary array
   </td>
   <td>(Json file name) + <code>_ID.nmrJson</code>
   </td>
  </tr>
</table>



    However if the URL flag in Flags object is true, the address to binary files are stored in the “reference” Object. The address not only can be a URL to a local computer/disk but also can be a URL to a cloud storage.

As we mentioned, data get stored as an N dimenssional array. For adding a new dimension, a dimension object should get added to Json. Every dimension has three json sub object, namely, id, info and parameters object. ‘id’ indicates the dimension of object. The table below shows details of ‘info’ sub element:


<table>
  <tr>
   <td>Field
   </td>
   <td>Type
   </td>
   <td>Description
   </td>
  </tr>
  <tr>
   <td><code>NumberOfSamples </code>
   </td>
   <td><code>Integer </code>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><code>Nucleus </code>
   </td>
   <td><code>String </code>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><code>AcquisitionBandwidth </code>
   </td>
   <td><code>Float </code>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><code>CarrierFrequency </code>
   </td>
   <td><code>Float </code>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><code>ObservationFrequency </code>
   </td>
   <td><code>Float </code>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><code>CentralChemicalShift </code>
   </td>
   <td><code>Float </code>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><code>ZeroOrderPhase </code>
   </td>
   <td><code>Float </code>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><code>DimensionInfo </code>
   </td>
   <td><code>String </code>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><code>DimensionUnit </code>
   </td>
   <td><code>String </code>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><code>Encoding </code>
   </td>
   <td><code>String </code>
   </td>
   <td>
   </td>
  </tr>
  <tr>
   <td><code>EncodingSteps </code>
   </td>
   <td><code>Integer[]</code>
   </td>
   <td>
   </td>
  </tr>
</table>


The specific parameters of each dimension get stored in ‘parameter’ object as a series of JSON array. For example, if dimension X stores the metabolite info, the object would be like the following:


```
"parameters": {
        "Metabolite": [
          "Naa",
          "Cho"
        ]
}
```


	

Or for multi parameters, it would be like below:


```
"parameters": {
        "TE": [
          20000.0
        ]
	  "TE1": [
          10000.0
        ]
      }
