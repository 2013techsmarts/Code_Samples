// Function to position bubbles randomly
function positionBubbles() {
    const bubbles = document.querySelectorAll('.bubble');
    bubbles.forEach(bubble => {
        const top = Math.random() * 80; // Random top position (0% to 80%)
        const left = Math.random() * 80; // Random left position (0% to 80%)
        bubble.style.top = `${top}%`;
        bubble.style.left = `${left}%`;
    });
}

// Function to make bubbles draggable
function makeBubblesDraggable() {
    const bubbles = document.querySelectorAll('.bubble');
    const waterAudio = document.getElementById('water-audio');

    bubbles.forEach(bubble => {
        let isDragging = false;
        let offsetX, offsetY;

        bubble.addEventListener('mousedown', (e) => {
            isDragging = true;
            offsetX = e.clientX - bubble.getBoundingClientRect().left;
            offsetY = e.clientY - bubble.getBoundingClientRect().top;
            bubble.style.cursor = 'grabbing';
            bubble.classList.add('dragging'); // Add dragging effect

            // Play water sound
            waterAudio.currentTime = 0; // Reset audio to start
            waterAudio.play(); // Play audio
        });

        document.addEventListener('mousemove', (e) => {
            if (isDragging) {
                const x = e.clientX - offsetX;
                const y = e.clientY - offsetY;
                bubble.style.left = `${x}px`;
                bubble.style.top = `${y}px`;
            }
        });

        document.addEventListener('mouseup', () => {
            if (isDragging) {
                isDragging = false;
                bubble.style.cursor = 'pointer';
                bubble.classList.remove('dragging'); // Remove dragging effect

                // Pause and reset water sound
                waterAudio.pause(); // Pause audio
                waterAudio.currentTime = 0; // Reset audio to start
            }
        });
    });
}

// Function to handle the Next button click
function handleTechDetailsButtonClick() {
    // Navigate to the next screen (replace 'tech-details-screen.html' with your desired URL)
    window.location.href = '/tech-details';
}

// Function to handle the Next button click
function handleRefDetailsButtonClick() {
    window.location.href = '/ref-details';
}

// Function to handle the Next button click
function handleThanksButtonClick() {
    window.location.href = '/thank-you';
}

// Initialize functions when the page loads
window.onload = () => {
    positionBubbles();
    makeBubblesDraggable();

    // Get the Next button and add an event listener
    const nextButton = document.getElementById('next-button');
    if (nextButton) {
        nextButton.addEventListener('click', handleTechDetailsButtonClick);
    }

    // Get the Ref button and add an event listener
    const refButton = document.getElementById('ref-button');
    if (refButton) {
        refButton.addEventListener('click', handleRefDetailsButtonClick);
    }

    // Get the Ref button and add an event listener
    const thanksButton = document.getElementById('thanks-button');
    if (refButton) {
        refButton.addEventListener('click', handleThanksButtonClick);
    }
};



