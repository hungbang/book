<%--
  Created by IntelliJ IDEA.
  User: KAI
  Date: 4/27/17
  Time: 11:51 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Image demo with dropzonejs and spring mvc</title>
    <script src="/js/dropzone.js" ></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap3-dialog/1.35.4/js/bootstrap-dialog.min.js"></script>
    <link href="/dropzone/dist/dropzone.css" rel="stylesheet" />


    <style>
        .dropzone{
            padding: 0px 20px !important;
        }

    </style>

</head>
<body>
        <h1>Form upload</h1>
        <div class="panel-body">

            <div>

                <form action="handleFileUpload" method="post" class="dropzone needsclick dz-clickable" id="dropzone-form" enctype="multipart/form-data">
                    <div class="dz-default dz-message file-dropzone text-center well col-sm-12">

                        <span class="glyphicon glyphicon-paperclip"></span>
                        <span>To attach files, drag and drop here</span><br>
                        <span>OR</span><br>
                        <span>Just Click</span>

                    </div>


                    <input type="hidden" name="userId" class="userId" value="2050" />
                    <input type="hidden" name="searchContactDate" class="searchContactDate" value="2017/04/29" />

                    <div class="dropzone-previews"></div>
                </form>

                <button id="upload-button" class="btn btn-primary">
                    <span class="glyphicon glyphicon-upload"></span> Upload
                </button>

                <button id="reset-button" class="btn btn-warning">
                    <span class="glyphicon glyphicon-refresh"></span> Reset Form
                </button>

            </div>
        </div>


</body>


<script>


    Dropzone.options.dropzoneForm = {

//        url : "upload", // not required if the <form> element has action attribute
        autoProcessQueue : false,
        uploadMultiple : false,
        maxFilesize : 2000, // MB
        parallelUploads : 10,
        maxFiles : 2000,
        addRemoveLinks : true,
        previewsContainer : ".dropzone-previews",

        // The setting up of the dropzone
        init : function() {

            var myDropzone = this;

            // first set autoProcessQueue = false
            $('#upload-button').on("click", function(e) {
                myDropzone.processQueue();

            });

            $('#reset-button').on('click', function(e){
               myDropzone.removeAllFiles();
            });


            //upload continue queue
            myDropzone.on("success", function(file, responseText){
                myDropzone.options.autoProcessQueue = true;
                file.previewTemplate.appendChild(document.createTextNode(responseText));
            });


            //when all files finish uploading
            myDropzone.on("queuecomplete", function () {
                myDropzone.options.autoProcessQueue = false;
            });

            // customizing the default progress bar
            myDropzone.on("uploadprogress", function(file, progress) {
                progress = parseFloat(progress).toFixed(0);
                var progressBar = file.previewElement.getElementsByClassName("dz-upload")[0];
                progressBar.innerHTML = progress + "%";
            });



            //when file upload complete, if upload sucess , then remove file
            myDropzone.on("complete", function(file){


//                alert(file.status +"======="+Dropzone.SUCCESS);

//                if(file.status == Dropzone.SUCCESS){
//                    myDropzone.removeFile(file);
//                }
            });
        }
    }



</script>

</html>
