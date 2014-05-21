from PIL import Image
import PIL.ImageOps    
import glob
import os.path

for f in glob.glob("Images/*.png"):
    f = f.replace("\\", "/")
    if "zinv_" not in f and os.path.isfile(f.replace("Images/", "Images/zinv_")) == False:
        print "Converted " + f
        image = Image.open(f)
        image = image.convert("RGB")
        inverted_image = PIL.ImageOps.invert(image)
        inverted_image.save(f.replace("Images/", "Images/zinv_"))
