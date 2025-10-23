document.getElementById("generate").addEventListener("click", function() {
  const length = parseInt(document.getElementById("length").value);
  const useDigits = document.getElementById("digits").checked;
  const useUpper = document.getElementById("upper").checked;
  const useLower = document.getElementById("lower").checked;
  const output = document.getElementById("output");

  let chars = "";
  if (useDigits) chars += "0123456789";
  if (useUpper) chars += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  if (useLower) chars += "abcdefghijklmnopqrstuvwxyz";

  if (chars === "") {
    alert("Please select at least one character type!");
    return;
  }

  if (!length || length <= 0) {
    alert("Please enter a valid length!");
    return;
  }

  let result = "";
  for (let i = 0; i < length; i++) {
    const randomIndex = Math.floor(Math.random() * chars.length);
    result += chars[randomIndex];
  }

  output.value = result;
});
